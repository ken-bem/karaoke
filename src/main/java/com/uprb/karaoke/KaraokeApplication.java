package com.uprb.karaoke;

import com.uprb.karaoke.Sockets.DataServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.stereotype.Controller;

@SpringBootApplication
public class KaraokeApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(KaraokeApplication.class, args);
        new DataServer().start();

    }

}
