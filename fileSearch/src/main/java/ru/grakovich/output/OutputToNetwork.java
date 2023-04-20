package ru.grakovich.output;

import java.io.*;
import java.net.Socket;
import java.util.List;



/**
 * @author Grakovich 20.04.2023
 */
public class OutputToNetwork implements OutFiles {
    private final long size = 60000L;
    private final String remoteHost = "HOST_NAME_HERE";
    private final String username = "USERNAME_HERE";
    private final String password = "PASSWORD_HERE";

    @Override
    public void outputFiles(List<File> files) {
        if(files.size()==0) {
            System.out.println("not file");
            return;
        }
        for (File file : files){
            if(file.length()<size){
                outFileToNetwork(file);
            }
        }
    }

    private void outFileToNetwork(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());

            Socket socket = new Socket("localhost", 1234);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String fileName = file.getName();
            byte[] fileNameBytes = fileName.getBytes();

            byte[] fileContentBytes = new byte[(int) file.length()];
            fileInputStream.read(fileContentBytes);

            dataOutputStream.writeInt(fileNameBytes.length);
            dataOutputStream.write(fileNameBytes);

            dataOutputStream.writeInt(fileContentBytes.length);
            dataOutputStream.write(fileContentBytes);

        } catch (IOException e) {
            throw new IllegalArgumentException("Передача не состоялась");
        }
    }
}



