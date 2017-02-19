package hu.mathgame.persist;

import java.io.Serializable;

public class NumberPK implements Serializable{
    private int columnindex;
    private int lineindex;
    private User user;
    
    public NumberPK() {
        // TODO Auto-generated constructor stub
    }


    
    public int getColumnindex() {
        return columnindex;
    }


    
    public void setColumnindex(int columnindex) {
        this.columnindex = columnindex;
    }


    
    public int getLineindex() {
        return lineindex;
    }


    
    public void setLineindex(int lineindex) {
        this.lineindex = lineindex;
    }
    
    
    public User getUser() {
        return user;
    }
    
    
    public void setUser(User user) {
        this.user = user;
    }
    
}
