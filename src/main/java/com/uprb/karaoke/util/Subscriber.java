package com.uprb.karaoke.util;

import com.uprb.karaoke.model.Player;
import lombok.Data;

import java.io.OutputStream;
import java.io.PrintStream;

@Data
public class Subscriber {

    private OutputStream printStream;

    private Player player;

    public Subscriber(Player player){
        this.player = player;
    }
}
