package com.uprb.karaoke.Sockets;

import com.uprb.karaoke.Game.GameController;
import com.uprb.karaoke.model.Game;
import com.uprb.karaoke.model.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.util.List;

public class DataServer{

    public static final int PORT = 9001;
    public static List<Player> players = new ArrayList<Player>();
    public Game game;

    private ServerSocket serverSocket;

    public void start(){
        try {
//          Server Started
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started at port " + PORT);
//          Player Subscribe
            while(!serverSocket.isClosed()){
                Socket newPlayer = serverSocket.accept();
                Player connected = new Player(newPlayer);
                connected.start();
                players.add(connected);
//              Game Start
                if(players.size()>= 3){
                    System.out.println("Game Starting...");
                    game = new GameController().setup(players);
                    game.start();
                }
            }
        }
        catch (Exception e){ e.printStackTrace();}
        finally { stop(); }
    }

    public void stop(){
        try {
            serverSocket.close();
        }catch (Exception e){e.printStackTrace();}
    }


    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try{
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            }catch (IOException err){
                System.out.println(err);
            }

        }
    }

}
