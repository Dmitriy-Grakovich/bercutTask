package ru.grakovich.output;

import java.io.File;
import java.util.List;

/**
 * @author Grakovich 18.04.2023
 */
public class OutputToConsole implements OutFiles {

    @Override
    public void outputFiles(List<File> files) {
        if (files.size() == 0) {
            System.out.println("not file");
            return;
        }
        files.forEach(System.out::println);
    }
}
