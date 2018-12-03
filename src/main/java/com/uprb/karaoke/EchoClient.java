package com.uprb.karaoke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class EchoClient {

    private String host;
    private int port;
    private String nickname;

    public static void main(String[] args) throws UnknownHostException, IOException {
        new EchoClient("127.0.0.1", 12345).run();
    }

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws UnknownHostException, IOException {
        // connect client to server
        Socket client = new Socket(host, port);
        System.out.println("Client successfully connected to server!");

        // create a new thread for server messages handling
        new Thread(new ReceivedMessagesHandler(client.getInputStream())).start();

        // ask for a nickname
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a nickname: ");
        nickname = sc.nextLine();

        // read messages from keyboard and send to server
        System.out.println("Send messages: ");
        PrintStream output = new PrintStream(client.getOutputStream());
        while (sc.hasNextLine()) {
            output.println(nickname + ": " + sc.nextLine());
        }

        output.close();
        sc.close();
        client.close();
    }
}

class ReceivedMessagesHandler implements Runnable {

    private InputStream server;

    public ReceivedMessagesHandler(InputStream server) {
        this.server = server;
    }

    @Override
    public void run() {
        // receive server messages and print out to screen
        Scanner s = new Scanner(server);
        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
        }
        s.close();
    }
}
