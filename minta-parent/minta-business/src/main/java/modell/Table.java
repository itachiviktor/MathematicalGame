/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modell;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kiss
 */
public class Table {
    private final int line;
    private final int column;
    private final Field[][] matrix;
    
    public Table(int line, int column) {
        this.line = line;
        this.column = column;
        this.matrix = new Field[line][column];
        
        for(int i=0;i<line;i++){
            for(int j=0;j<column;j++){
                this.matrix[i][j] = new Field(i, j);
                /*Üres valueval létrehozom a fieldeket*/
            }
        }
    }
    
    public Field returnField(int line, int column){
        return matrix[column][line];
    }
    
    public void moveFromInventoryToGameBoard(Number number){
        Field field = matrix[number.getColumn()][number.getLine()];
        field.setChild(number);
    }
    
    
    /*
    *This method give the Fields, where this number can move.
     */
    public List<Field> whereCanNumberMove(Number client){
        int value = client.getValue();
        int sLine = client.getLine();
        int sColumn = client.getColumn();
        
        List<Field> result = new ArrayList<>();
        
        int top = 1;
        int left = 1;
        int right = 1;
        int down = 1;
        int topleft = 1;
        int topright = 1;
        int downleft = 1;
        int downright = 1;
        
        for(int i=0;i<value;i++){
            if(sLine - top >= 0 && matrix[sLine - top][sColumn].getChild() == null){
                /*Ilyenkor felfelé még mehet*/
                result.add(matrix[sLine - top][sColumn]);
                top++;
            }
            
            if(sLine + down < this.line && matrix[sLine + down][sColumn].getChild() == null){
                /*Ilyenkor felfelé még mehet*/
                result.add(matrix[sLine + down][sColumn]);
                down++;
            }
            
            if(sColumn - left >= 0 && matrix[sLine][sColumn - left].getChild() == null){
                /*Ilyenkor felfelé még mehet*/
                result.add(matrix[sLine][sColumn - left]);
                left++;
            }
            
            if(sColumn + right < this.column && matrix[sLine][sColumn + right].getChild() == null){
                /*Ilyenkor felfelé még mehet*/
                result.add(matrix[sLine][sColumn + right]);
                right++;
            }
            
            if(sLine - topleft >= 0 && sColumn - topleft >= 0 && 
                    matrix[sLine - topleft][sColumn - topleft].getChild() == null){
                /*Ilyenkor felfelé még mehet*/
                result.add(matrix[sLine - topleft][sColumn - topleft]);
                topleft++;
            }
            
            if(sLine - topright >= 0 && sColumn + topright < this.column 
                    && matrix[sLine - topright][sColumn + topright].getChild() == null){
                /*Ilyenkor felfelé még mehet*/
                result.add(matrix[sLine - topright][sColumn + topright]);
                topright++;
            }
            
            if(sLine + downleft < this.line && sColumn - downleft >= 0 && 
                    matrix[sLine + downleft][sColumn - downleft].getChild() == null){
                /*Ilyenkor felfelé még mehet*/
                result.add(matrix[sLine + downleft][sColumn - downleft]);
                downleft++;
            }
            
            if(sLine + downright < this.line && sColumn + downright < this.column && 
                    matrix[sLine + downright][sColumn + downright].getChild() == null){
                /*Ilyenkor felfelé még mehet*/
                result.add(matrix[sLine + downright][sColumn + downright]);
                downright++;
            } 
        }
        
        return result;
    }
       
}