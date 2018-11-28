package com.uprb.karaoke.model;

import com.uprb.karaoke.Sockets.DataServer;
import lombok.Data;

import java.net.Socket;
import java.io.*;

@Data
public class Player extends Thread{

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private Long id;
    private String username;
    private String color;
    private int score;
    private int highscore;

    public Player(Socket socket) throws IOException{
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        System.out.println("Someone Connected");
//        setUserName(input.readUTF());
//        setColor(input.readUTF());

        return;
    }

    public void setUserName(String userName){
        this.username = userName;
    }

    public void setHighscore(int highscore){
        this.highscore = highscore;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void removePlayer() throws IOException{
        socket.close();
        input.close();
        DataServer.players.remove(this);
    }

//    public Player(Socket socket,String username, String color, String[] answers){
//        this.socket = socket;
//        this.username = username;
//        this.color = color;
//        try{
//            input = new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()));
//            output = new PrintWriter(socket.getOutputStream(), true);
//            output.println("WELCOME " + username);
//            output.println("MESSAGE Waiting for opponent to connect");
//        }catch (IOException err ){
//            System.out.println("Player died: " + err);
//        }
//    }




}
