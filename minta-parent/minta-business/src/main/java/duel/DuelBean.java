/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duel;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import modell.Duel;
import modell.Player;

/**
 *
 * @author Kiss
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class DuelBean{
    private List<Player> waitingPlayers;
    private List<Duel> duels;

    public DuelBean() {
    }

    
    @PostConstruct
    public void init(){
        this.waitingPlayers = new ArrayList<>();
        this.duels = new ArrayList<>();
    }
    
    @Lock(LockType.WRITE)
    public void login(Player player){
        waitingPlayers.add(player);
        /*try to find a duel partner here*/
        for(int i=0;i<waitingPlayers.size();i++){
            if(waitingPlayers.get(i) != player){
                this.duels.add(new Duel(waitingPlayers.get(i), player));
            }
        }
    }
    
    @Lock(LockType.READ)
    public Duel hasMeAnyDuelPartner(Player player){
        for(int i=0;i<duels.size();i++){
            if(duels.get(i).getPlayer1() == player ||
                    duels.get(i).getPlayer2() == player){
                return duels.get(i);
            }
        }
        return null;
    }
    
    @Lock(LockType.WRITE)
    public void removeDuel(Duel duel){
        this.duels.remove(duel);
    }
    
    @Lock(LockType.WRITE)
    public void removeMeFromWaiters(Player player){
        this.waitingPlayers.remove(player);
    }

    public List<Player> getWaitingPlayers() {
        return waitingPlayers;
    }

    public void setWaitingPlayers(List<Player> waitingPlayers) {
        this.waitingPlayers = waitingPlayers;
    }

    public List<Duel> getDuels() {
        return duels;
    }

    public void setDuels(List<Duel> duels) {
        this.duels = duels;
    }
    
    
}
