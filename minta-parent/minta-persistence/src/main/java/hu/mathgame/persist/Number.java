package hu.mathgame.persist;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name="NUMBER")
@IdClass(NumberPK.class)
public class Number{
    @Id
    private int columnindex;
    @Id
    private int lineindex;
    
    
    private int nvalue;

    
    
    private String lifecycle;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "muserfk")
    private User user;
    
    
    @OneToOne
    @JoinColumn(name="fieldfk")
    private Field field;
    
    
    public Number() {
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


    
    public int getNvalue() {
        return nvalue;
    }


    
    public void setNvalue(int nvalue) {
        this.nvalue = nvalue;
    }


    
    public String getLifecycle() {
        return lifecycle;
    }


    
    public void setLifecycle(String lifecycle) {
        this.lifecycle = lifecycle;
    }


    
    public Field getField() {
        return field;
    }


    
    public void setField(Field field) {
        this.field = field;
    }
    
    
    public User getUser() {
        return user;
    }
    
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return "columnindex: " + columnindex + " lifecycle: " + lifecycle;
    }
    
}



