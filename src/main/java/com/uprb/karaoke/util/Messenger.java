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

    public String send(){
        return Json.toJson(this);
    }

}
