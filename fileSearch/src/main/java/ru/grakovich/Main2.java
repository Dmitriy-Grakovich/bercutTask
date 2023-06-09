package ru.grakovich;

import ru.grakovich.output.OutFiles;
import ru.grakovich.output.OutputToConsole;
import ru.grakovich.service.SearchFileIsDirectory;

import java.io.File;


/**
 * @author Grakovich 18.04.2023
 */
public class Main2 {
    public static void main(String[] args) {

        String fileName = args.length > 0 ? args[0] : "*myfi*";
        File file = new File(args.length > 1 ? args[1] : "D:\\java");

        SearchFileIsDirectory searchFileIsDirectory = new SearchFileIsDirectory(file, fileName);

        OutFiles outFiles = new OutputToConsole();
        outFiles.outputFiles(searchFileIsDirectory.files());


    }
}
