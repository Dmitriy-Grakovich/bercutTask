package ru.grakovich.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;


/**
 * @author Grakovich 18.04.2023
 */

public class SearchFileIsDirectory implements SearchFile {


    private final File file;
    private final List<File> fileList = new ArrayList<>();
    private final String search;
    private final CopyOnWriteArraySet<Future<?>> taskList = new CopyOnWriteArraySet<>();

    public SearchFileIsDirectory(File file, String search) {
        this.file = file;
        this.search = search;
    }

    @Override
    public List<File> files() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        searchF(file, executorService);
        if (taskEnd()) {
            executorService.shutdown();
        }
        return fileList;
    }

    private void searchF(File file, ExecutorService executorService) {

        Runnable runnable = () -> {
            if (file.isDirectory()) {
                for (File i : Objects.requireNonNull(file.listFiles())) {
                    searchF(i, executorService);
                }
            } else {
                if ((file.getName().contains((search.replaceAll("[*]", ""))))) {
                    fileList.add(file);
                }
            }
        };
        taskList.add(executorService.submit(runnable));
    }

    private boolean taskEnd() {
        boolean temp;
        do {
            temp = true;
            for (Future<?> future : taskList) {
                temp &= future.isDone();
            }
        } while (!temp);
        return true;
    }
}
