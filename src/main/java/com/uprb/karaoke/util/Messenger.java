package com.uprb.karaoke.util;

import com.uprb.karaoke.model.Player;
import lombok.Data;

import java.io.OutputStream;
import java.io.PrintStream;

@Data
public class Messenger {

    private String message;

    private String status;

    private String payload;

    public Messenger() {
    }

    public Messenger(String message, String status, String payload) {
        this.message = message;
        this.status = status;
        this.payload = payload;
    }

    public String send(){
        return Json.toJson(this);
    }

    public static Messenger getMessenger(String message, String status, String payload){
        return new Messenger(message,status,payload);
    }
}
