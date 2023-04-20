package ru.grakovich.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Grakovich 20.04.2023
 */
public class MyServer {
    private final int port = 1234;
    private final String fileDirectory = "D:\\test\\";


    public void start() {
        int i = 0;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                int fileNameLength = dataInputStream.readInt();
                if (fileNameLength > 0) {
                    byte[] fileNameBytes = new byte[fileNameLength];
                    dataInputStream.readFully(fileNameBytes, 0, fileNameLength);
                    String fileName = new String(fileNameBytes).replaceAll("[.]", i++ + ".");

                    int fileContentLength = dataInputStream.readInt();
                    File file = new File(fileDirectory + fileName);
                    if (fileContentLength > 0) {
                        byte[] fileContentBytes = new byte[fileContentLength];
                        dataInputStream.readFully(fileNameBytes, 0, fileContentLength);
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(fileContentBytes);
                        fileOutputStream.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("что то сломалось");
        }
    }
}
