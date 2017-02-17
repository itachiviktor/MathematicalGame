package hu.mathgame.persist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "FIELD")

public class Field implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "field_id")
    @SequenceGenerator(name = "field_id", sequenceName = "FIELD_ID")
    @Column(name = "fieldID", precision = 20, scale = 0)
    private int id;

    private int columnindex;

    private int rowindex;

    @ManyToOne
    @JoinColumn(name = "mtablefk")
    private Table fktable;

    public Field() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColumnindex() {
        return columnindex;
    }

    public void setColumnindex(int columnindex) {
        this.columnindex = columnindex;
    }

    public int getRowindex() {
        return rowindex;
    }

    public void setRowindex(int rowindex) {
        this.rowindex = rowindex;
    }

    @Override
    public String toString() {
        return id + " " + columnindex + " " + rowindex;
    }

}
