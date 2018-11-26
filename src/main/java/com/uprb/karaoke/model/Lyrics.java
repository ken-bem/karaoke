package com.uprb.karaoke.model;

import lombok.Data;

import java.util.List;

@Data
public class Lyrics {

    private Long id;
    private String path;

    private List<String> rows;
}
