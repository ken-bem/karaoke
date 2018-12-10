package com.uprb.karaoke.model;


import com.uprb.karaoke.model.lyrics.Lyric;
import lombok.Data;

@Data
public class Song {

    private Long id;
    private String title;
    private String artist;
    private String album;
    private String cover;

    private Lyric lyrics;

    private double length;
    private int popularity;

    public Song(){

    }



}
