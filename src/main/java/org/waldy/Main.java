package org.waldy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try{
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Starting port from server at: " + serverSocket.getLocalPort());
            while (true){
                Socket socket = serverSocket.accept();
                ImplementServer srv = new ImplementServer(socket);
                Thread t = new Thread(srv);
                Thread.sleep(100);
                t.start();
            }

        } catch (IOException ex){
            ex.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}