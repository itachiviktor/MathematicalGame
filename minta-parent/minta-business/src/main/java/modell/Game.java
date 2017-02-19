/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

/**
 *
 * @author Kiss
 */
public class Game {
    private Player host;
    private Player quest;
    private Table table;

    public Game(Player host, Player quest, int line, int column) {
        this.host = host;
        this.quest = quest;
        this.table = new Table(line, column);
    }

    public Player getHost() {
        return host;
    }

    public Player getQuest() {
        return quest;
    }

    public Table getTable() {
        return table;
    }

    public void setHost(Player host) {
        this.host = host;
    }

    public void setQuest(Player quest) {
        this.quest = quest;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    
    /*This method gain a username, and answer, that this user is in this game or not.*/
    public synchronized boolean isItThesearchedGame(String username){
        if(this.host.getUsername().equals(username) || this.quest.getUsername().equals(username)){
            return true;
        }
        return false;
    }
    
    
    
}