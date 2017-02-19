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
public class Inventory {
    private List<Number> numbers;

    public Inventory() {
        numbers = new ArrayList<>();
        numbers.add(new modell.Number(1));
        numbers.add(new modell.Number(1));
        numbers.add(new modell.Number(2));
        numbers.add(new modell.Number(2));
        numbers.add(new modell.Number(3));
        numbers.add(new modell.Number(3));
        numbers.add(new modell.Number(4));
        numbers.add(new modell.Number(4));
        numbers.add(new modell.Number(5));
        numbers.add(new modell.Number(5));
        numbers.add(new modell.Number(6));
        numbers.add(new modell.Number(6));
    }
    
    /*Nem kitöröl a listából, mert akkor ai indexelések elcsúsznának, hanm annyit csinál, hogy ahonnan
    kitörlés történt, azt az indexet nullra állítja.*/
    public void removeFromInventory(int index){
        numbers.add(index, null);
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }
    
    
    
    
}
