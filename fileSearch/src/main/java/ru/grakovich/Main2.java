package ru.grakovich;


import ru.grakovich.output.OutputToConsole;
import ru.grakovich.service.SearchFileIsDirectoryImpl;

import java.io.File;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Grakovich 18.04.2023
 */
public class Main2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Exchanger<File> exchanger = new Exchanger<>();


        SearchFileIsDirectoryImpl searchFileIsDirectory = new SearchFileIsDirectoryImpl(executorService,new File("D:\\java"), "*myfil*");


        List<File> files = searchFileIsDirectory.files();

        OutputToConsole outputToConsole = new OutputToConsole();
        outputToConsole.outputFiles(files);



        executorService.shutdown();

    }


}
