package ru.grakovich.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @author Grakovich 18.04.2023
 */
public class SearchFileIsDirectoryImpl implements SearchFileIsDirectory {


    private final ExecutorService executorService;
    private final List<File> fileList = new ArrayList<>();
    private final String search;
    private final File[] files;


    public SearchFileIsDirectoryImpl(ExecutorService executorService, File file, String search) {
        this.executorService = executorService;
        this.search = search;
        files = file.listFiles();
    }


    @Override
    public List<File> files() {

        searchF(files);

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return fileList;
    }

    private void searchF(File[] files) {

        Runnable runnable = () -> {
            for (File i:files) {
                if(i.isDirectory()){
                    searchF(i.listFiles());
                } else {

                    if((i.getName().contains((search.replaceAll("[*]",""))))){
                        fileList.add(i);
                    }
                }
            }
        };
       executorService.submit(runnable);
    }


}
