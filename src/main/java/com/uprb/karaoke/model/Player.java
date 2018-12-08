package com.uprb.karaoke.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.Socket;
import java.io.*;

@Data
public class Player implements Serializable{

    private Socket socket;
    private BufferedReader input;
    private PrintStream output;
    private Long id;
    private String username;
    private String color;
    private int score;
    private int highscore;

    public Player(){}

    public Player(Socket socket) throws IOException{
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintStream(socket.getOutputStream());
        this.output.println("Someone Connected");
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
    }

}
