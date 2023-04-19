package ru.grakovich.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;


/**
 * @author Grakovich 18.04.2023
 */
public class SearchFileIsDirectory {


    private final ExecutorService executorService;
    private final List<File> fileList = new ArrayList<>();
    private final String search;


    public SearchFileIsDirectory(ExecutorService executorService, String search) {
        this.executorService = executorService;
        this.search = search;

    }

    public List<File> files() {

        return fileList;
    }

    public void searchF(File[] files) {

        Runnable runnable = () -> {
            for (File i : files) {
                if (i.isDirectory()) {
                    searchF(i.listFiles());
                } else {

                    if ((i.getName().contains((search.replaceAll("[*]", ""))))) {
                        fileList.add(i);
                    }
                }
            }
        };
        executorService.submit(runnable);
    }


}
