package com.uprb.karaoke.Game;

import com.uprb.karaoke.model.Game;
import com.uprb.karaoke.model.Player;
import com.uprb.karaoke.model.Song;
import org.springframework.stereotype.Controller;

import java.util.List;

public class GameController {

    /**
     * 1.List of players
     * 2.Song
     *
     * */

    public Game setup(List<Player> players){

        if(players == null || players.isEmpty() || players.size() < 3) return null; //GTFO

        Game game = new Game();
        game.setPlayerList(players);
//        game.setSong(song);

        return game;
    }

}
