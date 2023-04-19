package ru.grakovich.service;

import java.io.File;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author Grakovich 18.04.2023
 */
public interface SearchFileIsDirectory {
    List<File> files();

}
