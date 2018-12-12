package com.uprb.karaoke.model.lyrics;

import lombok.Data;
import java.util.List;

@Data
public class Song {

    private Long id;
    private String title;
    private String artist;
    private String album;
    private String cover;

    private List<Sentence> sentences;
    private Lyric lyrics;

    private double length;

    public Song(){}



}
