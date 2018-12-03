package com.uprb.karaoke.Sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class DataServer {

    public static final int PORT = 9001;

    private ServerSocket serverSocket;

    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);
            while (true)
                new EchoServer(serverSocket.accept()).start();

        }
        catch (Exception e){ e.printStackTrace();}
        finally { stop(); }
    }

    public void stop(){
        try {
            serverSocket.close();
        }catch (Exception e){e.printStackTrace();}
    }


    private static class EchoServer extends Thread{

        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoServer(Socket socket){ }

        public void run(){
            try{
                out = new PrintWriter(clientSocket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;

                while((inputLine = in.readLine()) != null){
                    if(".".equals(inputLine)){
                        out.println("bye");
                        break;
                    }

                    out.println(inputLine);
                }


                in.close();
                out.close();
                clientSocket.close();


            }catch (IOException e){

            }
        }
   }

}
