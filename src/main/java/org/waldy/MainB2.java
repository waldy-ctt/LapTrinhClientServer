package org.waldy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainB2 {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(8081);
            System.out.println("Starting port from server at: " + serverSocket.getLocalPort());
            while (true){
                Socket socket = serverSocket.accept();
                ImplementServerB2 srv = new ImplementServerB2(socket);
                Thread t = new Thread(srv);
                Thread.sleep(100);
                t.start();

            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}