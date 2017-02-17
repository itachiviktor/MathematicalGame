package hu.mathgame.persist;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "MTABLE")
@NamedQueries({
        @NamedQuery(name = "Table.findAll", query = ""
                + "SELECT smr "
                + "FROM MTABLE smr ") })
public class Table implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "table_id")
    @SequenceGenerator(name = "table_id", sequenceName = "TABLE_ID")
    @Column(name = "tableID", precision = 20, scale = 0)
    private Integer id;

    private int turn;

    @OneToMany(mappedBy = "table", targetEntity = User.class, fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "fktable", targetEntity = Field.class, fetch = FetchType.LAZY)
    private List<Field> fields;

    public Table() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return id + " " + turn;
    }

}
