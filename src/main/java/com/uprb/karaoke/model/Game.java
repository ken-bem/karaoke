package com.uprb.karaoke.model;

import com.uprb.karaoke.model.lyrics.LrcParser;
import com.uprb.karaoke.model.lyrics.Lyric;
import com.uprb.karaoke.model.lyrics.Sentence;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Game{

    private Long id;
    private List<Player> playerList;

    private Song song;
    private Sentence currSentence;
    private boolean started;
    private GameStatus gameStatus;

    private int viewers;
    private int score;
    private Map<Player, Integer> highscore;

    public void setup(){
        this.playerList = new ArrayList<>();
        this.started = false;
        this.gameStatus = GameStatus.WAITING_FOR_PLAYERS;
    }

    public void addPlayer(Player player){
        this.getPlayerList().add(player);
    }

    public String getStatus(){
        return gameStatus.toString();
    }

    public void start() throws IOException {
        File file = new ClassPathResource("/static/abba.lrc")
                .getFile();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        Lyric lyric;
        try{
            lyric = LrcParser.create(reader);
            ArrayList<Sentence> sentences = lyric.findAllSentences(-1,-1);
            sentences

                    .forEach(sentence -> {
                        try {
                            sentence.setMissingWord();
                            System.out.println(sentence.showString());
                            Thread.sleep(sentence.getDuring());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });

            lyric.getTags();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
