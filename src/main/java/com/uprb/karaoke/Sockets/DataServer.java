package com.uprb.karaoke.Sockets;

import com.uprb.karaoke.model.Game;
import com.uprb.karaoke.model.Player;
import com.uprb.karaoke.util.Json;
import com.uprb.karaoke.util.Messenger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DataServer {

    private int port;
    private List<Player> clients;
    private ServerSocket server;
    private Game game;

    public static void main(String[] args) throws IOException {
        new DataServer(12345).run();
    }

    public DataServer(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.game = new Game();
        game.setup();
    }

    public void run() throws IOException {
        server = new ServerSocket(port) {
            protected void finalize() throws IOException {
                this.close();
            }
        };
        System.out.println("Port 12345 is now open.");
        System.out.println("Waiting For Players...");

        while (!game.isStarted()) {

            //adds new player to the game
            addPlayerToGame(server.accept());

            //sends the WAITING_FOR_PLAYERS signal
           broadcastMessages("Waiting for Players", "WAITING_FOR_PLAYERS", null);
            //Broadcast when a new player is added

            //Send the VOTE signal

            //Send the WAITING FOR VOTING signal after the person has voted

            //Send the Song is selected signal

            //UPDATE LYRICS

            //When the server receives the input,
            // check if its correct, and set who is the person who correctly answered.

            //Send the correct player and the output

        }

    }

    void addPlayerToGame(Socket client) throws IOException {

        Player player = new Player(client);
        System.out.println("Connection established with client: " + player.getSocket().getInetAddress().getHostAddress());

        this.clients.add(player);
        new Thread(new ClientHandler(this, player.getSocket().getInputStream())).start();

    }

    void broadcastMessages(String msg) {
        for (Player client : this.clients) {
            Messenger message = new Messenger();
            message.setMessage("Test");
            message.setStatus("Waiting_For_P");
            client.getOutput().println(msg);
        }
    }


    void broadcastMessages(String msg, String status, Object o){

        Messenger messenger = new Messenger();
        messenger.setMessage(msg);
        messenger.setStatus(status);
        messenger.setPayload(Json.toJson(o));

        for (Player client : this.clients){
            client.getOutput().println(messenger.send());
        }
    }


}

class ClientHandler implements Runnable {

    private DataServer server;
    private InputStream client;

    public ClientHandler(DataServer server, InputStream client) {
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {
        String message;

        // when there is a new message, broadcast to all
        Scanner sc = new Scanner(this.client);
        while (sc.hasNextLine()) {
            message = sc.nextLine();
            System.out.println(Json.fromJson(message, HashMap.class));
            server.broadcastMessages(message);
        }
        sc.close();
    }


}
