package ru.grakovich;

import ru.grakovich.server.MyServer;

/**
 * @author Grakovich 20.04.2023
 */
public class MainServer {
    public static void main(String[] args) {
        MyServer server = new MyServer();
        server.start();
    }
}
