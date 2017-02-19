package hu.mathgame.persist;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "MUSER")
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = ""
                + "SELECT smr "
                + "FROM MUSER smr ") })
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
    @SequenceGenerator(name = "user_id", sequenceName = "USER_ID")
    @Column(name = "userID", precision = 20, scale = 0)
    private int id;

    private String username;

    private String password;

    private int health;

    private int wins;

    private int loses;

    private int paired;

    @ManyToOne
    @JoinColumn(name = "mtablefk")
    private Table table;
    
    @OneToMany(mappedBy = "user", targetEntity = Number.class)
    private List<Number> numbers;


    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getPaired() {
        return paired;
    }

    public void setPaired(int paired) {
        this.paired = paired;
    }
    
    
    public List<Number> getNumbers() {
        return numbers;
    }
    
    
    public Table getTable() {
        return table;
    }
    
    
    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }
    
    
    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return password + " " + username + " " + health;
    }

}
