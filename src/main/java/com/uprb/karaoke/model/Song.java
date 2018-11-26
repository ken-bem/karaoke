package com.uprb.karaoke.model;

import lombok.Data;

@Data
public class Song {

    private Long id;
    private String title;
    private String artist;
    private String album;
    private String cover;

    private Lyrics lyrics;

    private double length;
    private int popularity;



}
