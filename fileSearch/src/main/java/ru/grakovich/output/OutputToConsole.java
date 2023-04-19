package ru.grakovich.output;

import java.io.File;
import java.util.List;

/**
 * @author Grakovich 18.04.2023
 */
public class OutputToConsole implements OutFiles{

    @Override
    public void outputFiles(List<File> files) {
        for (File i : files) {
            System.out.println(i.getAbsoluteFile());
        }
    }
}
