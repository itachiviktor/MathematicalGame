/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.util.List;

/**
 *
 * @author Kiss
 */
public class Player {
    private int health;
    private Inventory inventory;
    private String username;
    

    public Player(int health, Inventory inventory, String username) {
        this.health = health;
        this.inventory = inventory;
        this.username = username;
    }

    public int getHealth() {
        return health;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getUsername() {
        return username;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
