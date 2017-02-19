/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * @author Kiss
 */
@Stateful
@LocalBean
public class ButtonBean {
    private boolean enemyButton;
    
    
    public String buttonAction(String button){
        if("Enemy".equalsIgnoreCase(button)){
            enemyButton = !enemyButton;
        }else if("Honor".equalsIgnoreCase(button)){
            
        }else if("Surrender".equalsIgnoreCase(button)){
            
        }else{
            return "Not an existing button";
        }
        return null;
    }
}
