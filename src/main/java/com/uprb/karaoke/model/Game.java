package com.uprb.karaoke.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Game{

    private Long id;
    private List<Player> playerList;

    private Song song;

    private int viewers;
    private int score;
    private Map<Player, Integer> highscore;

    public void setup(){
        this.playerList = new ArrayList<>();
    }

    public void addPlayer(Player player){
        this.getPlayerList().add(player);
    }

}
