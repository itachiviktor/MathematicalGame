/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;

/**
 *
 * @author Kiss
 */
@Singleton
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class UserDatabase {
    private Map<String,String> users;

    public UserDatabase() {
    }
    
    @PostConstruct
    public void init(){
        this.users = new HashMap<>();
        users.put("fremen", "fremen");
        users.put("admin", "admin");
        users.put("itachiviktor", "vipmail");
    }

    @Lock(LockType.READ)
    public Map<String, String> getUsers() {
        return users;
    }
    
    @Lock(LockType.READ)
    public boolean isThisUserExistInDB(String username, String password){
        for(String x : users.keySet()){
            if(x.equals(username) && users.get(x).equals(password)){
                return true;
            }
        }
        return false;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
    
    
    
        
}
