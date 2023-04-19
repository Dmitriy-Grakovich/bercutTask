package ru.grakovich;

import ru.grakovich.output.OutFiles;
import ru.grakovich.output.OutputToConsole;
import ru.grakovich.service.SearchFileIsDirectory;
import ru.grakovich.service.SearchFileIsDirectoryImpl;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author Grakovich 18.04.2023
 */
public class Main2 {
    public static void main(String[] args) {
        String fileName = args.length > 0 ? args[0] : "*myfi*";
        File file = new File(args.length > 1 ? args[1]:"D:\\java");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        SearchFileIsDirectory searchFileIsDirectory = new SearchFileIsDirectoryImpl(executorService, file,fileName);
        List<File> files = searchFileIsDirectory.files();
        OutFiles outFiles = new OutputToConsole();
        outFiles.outputFiles(files);

        executorService.shutdown();

    }


}
