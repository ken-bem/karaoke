package com.uprb.karaoke.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game{

    private Long id;
    private List<Player> playerList = new ArrayList<>();

    private Song song;

    private int viewers;

    private int score;
    private int highscore;

    public void setPlayerList(List<Player> playerList){
        this.playerList = playerList;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
