package com.uprb.karaoke.Sockets;

import com.uprb.karaoke.model.Game;
import com.uprb.karaoke.model.GameStatus;
import com.uprb.karaoke.model.Player;
import com.uprb.karaoke.util.Json;
import com.uprb.karaoke.util.Messenger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

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

        while (game.getGameStatus().equals(GameStatus.WAITING_FOR_PLAYERS)) {
            //adds new player to the game
            addPlayerToGame(server.accept());
        }

        //Send the Song is selected signal
        game.start(clients);
        //UPDATE LYRICS

        //When the server receives the input,
        // check if its correct, and set who is the person who correctly answered.

        //Send the correct player and the output

    }

    void addPlayerToGame(Socket client) throws IOException {

        Player player = new Player(client);
        System.out.println("Connection established with client: " + player.getSocket().getInetAddress().getHostAddress());

        broadcastMessages("Player Joined", "PLAYER-JOIN", null);

        this.clients.add(player);
        new Thread(new ClientHandler(this, player)).start();

        player
                .getOutput()
                .println(Json.toJson(Messenger
                        .getMessenger("Waiting for Players", "WAITING_FOR_PLAYERS", null)));

        try {
            Thread.sleep(3000);
            player
                    .getOutput()
                    .println(Json.toJson(Messenger
                            .getMessenger("Game Started", GameStatus.STARTED.toString(), null)));

            game.setGameStatus(GameStatus.STARTED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void broadcastMessages(String msg) {
        for (Player client : this.clients) {
            Messenger message = new Messenger();
            message.setMessage("Test");
            message.setStatus("Waiting_For_PLAYERS");
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
    private Player player;

    public ClientHandler(DataServer server, Player client) throws IOException {
        this.server = server;
        this.player = client;
        this.client = client.getSocket().getInputStream();
    }

    @Override
    public void run() {
        String message;

        // when there is a new message, broadcast to all
        Scanner sc = new Scanner(this.client);
        while (sc.hasNextLine()) {
            message = sc.nextLine();
            processMessage(Json.fromJson(message,Messenger.class));
            System.out.println(Json.fromJson(message, HashMap.class));
            server.broadcastMessages(message);
        }
        sc.close();
    }

    public void processMessage(Messenger msg){
        switch (msg.getStatus()){
            case "CREATE_PLAYER":
                setPlayer(msg);
                server.broadcastMessages("New Player Added");
        }
    }

    private void setPlayer(Messenger messenger){
        HashMap info = Json.fromJson(messenger.getPayload(), HashMap.class);
        player.setUserName(info.get("username").toString());
        player.setColor(info.get("color").toString());
    }
}
