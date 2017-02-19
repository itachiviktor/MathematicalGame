/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kiss
 */
public class GameService {
    private List<Game> games = new ArrayList<>();
    
    
    public GameService() {
    }
    
    public synchronized void addNewGame(Game game){
        this.games.add(game);
    }
    
    public synchronized void removeGame(Game game){
        this.games.remove(game);
    }
}
