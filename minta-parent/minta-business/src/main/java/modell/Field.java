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
public class Field {
    private int line;
    private int column;
    private Number child;

    public Field() {
    }
    
    public Field(int line, int column){
        this.line = line;
        this.column = column;
    }

    public Field(int line, int column, Number child) {
        this(line, column);
        this.child = child;
    }

    public Number getChild() {
        return child;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    public void setChild(Number child) {
        this.child = child;
        if(child != null){
            child.setLine(this.line);
            child.setColumn(this.column);
        }
        
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setLine(int line) {
        this.line = line;
    }
    
    
}
