/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import modell.Field;
import modell.Inventory;
import modell.Player;
import modell.Table;
import org.json.JSONArray;
import org.json.JSONObject;

@Stateful
@LocalBean
/*Ezt a példányt esetleg két kliens is eléri?*/
public class GameBean {
    private Player host;
    private Player quest;
    private Table table;
    
    private int gameClicked = 0;
    private int inventoryClicked = 0;
    
    private int selectedInventoryIndex = -1;

    private List<Field> whereCanMove;
    /*A kiejlölt lehetséges mozgó elem elhelyeszkedése.*/
    private int maybeLine;
    private int maybeColumn;
    
    public GameBean() {
        table = new Table(10, 10);/*egyenlőre innen adjuk meg a táblát*/
        
        host = new Player(40, new Inventory(), "fremen");/*a hostot sem így kellene összerakni*/
    }

    
    
    public GameBean(Player host, Player quest, int line, int column) {
        this.host = host;
        this.quest = quest;
        this.table = new Table(line, column);
    }

    
    /*This method gain a username, and answer, that this user is in this game or not.*/
    @Lock(LockType.READ)
    public boolean isItThesearchedGame(String username){
        if(this.host.getUsername().equals(username) || this.quest.getUsername().equals(username)){
            return true;
        }
        return false;
    }
    
    public JSONObject calculateGameState(JSONObject parameters){
        int line = parameters.getInt("line");
        int column = parameters.getInt("column");
        
        String tableType = parameters.getString("tableType");
        
        JSONObject response = new JSONObject();
        
        if("Game".equalsIgnoreCase(tableType)){
            gameClicked++;
            if(gameClicked == 1){
                /*Itt meg kell vizsgálni, hogy inventoryClicked == 1, mert ha igen, és a táblára is
                első kattintás jön, akkor azt jelenti, hogy az inventoryból a táblára szertnénk pakolni.*/
                if(inventoryClicked == 1){
                    modell.Number selected = host.getInventory().getNumbers().get(selectedInventoryIndex);
                    host.getInventory().removeFromInventory(selectedInventoryIndex);/*itt kellene kitörölni a listából*/
                    selected.setLine(line);
                    selected.setColumn(column);
                    table.moveFromInventoryToGameBoard(selected);/*ezzel rakom rá a tablere*/
                    
                    /*Itt olyan JSON-t kell visszaadni, hogy hova rakta be az inventoryból az elemet
                    line.column(hova), és a value-t is. */

                    response.put("lineTo", line);
                    response.put("columnTo", column);
                    response.put("lineFrom", 0);
                    response.put("columnFrom", selectedInventoryIndex);
                    response.put("value", selected.getValue());
                    response.put("responseType", "InventoryMove");
                    
                    selectedInventoryIndex = -1;/*ezzel pedig a kiválasztott indexet kiválasztatléanra állítom*/
                    inventoryClicked = 0;
                    gameClicked = 0;
                }else{
                    /*Ilyenkor lett kiválasztva, hogy melyikkel szeretne mozogni, ilyenkor
                    visszaküldjük neki, hogy merre lépkedhet
                    */
               
                    JSONArray arr = new JSONArray();

                    whereCanMove = table.whereCanNumberMove(table.returnField(line, column).getChild());
                    
                    maybeLine = line;
                    maybeColumn = column;
                    for(int i=0;i<whereCanMove.size();i++){
                        JSONObject obj = new JSONObject();
                        obj.append("line", whereCanMove.get(i).getLine());
                        obj.append("column", whereCanMove.get(i).getColumn());
                        arr.put(obj);
                    }
                    
                    response.put("responseType", "GameMoveOptions");
                    response.put("options", arr);
                }
                

            }else if(gameClicked == 2){
                /*Ha magára kattint vagy olyan helyre ahova nem léphet, akkor nullára kell állítani
                a gameClicked állapotváltozót, ugyanis újra ki kell jelölnie a dolgokat. Ha olyan helyre
                kattint ahova léphet, akkor oda lép, majd az állapotváltozót szintén nullára kell állítani*/
                
                boolean odaLephet = false;
                for(int i=0;i<whereCanMove.size();i++){
                    if(whereCanMove.get(i).getColumn() == column && whereCanMove.get(i).getLine() == line){
                        odaLephet = true;
                        Field owner = table.returnField(maybeLine, maybeColumn);
                        modell.Number mover = owner.getChild();
                        Field newOwner = table.returnField(line, column);
                        newOwner.setChild(mover);
                        owner.setChild(null);
                        response.put("value", mover.getValue());
                        response.put("lineFrom", maybeLine);
                        response.put("columnFrom", maybeColumn);
                        break;
                    }
                }
                
                if(!odaLephet){
                    response.put("responseType", "StopSelection");
                    
                }else{
                    response.put("responseType", "GameMoveDestination");
                    response.put("lineTo", line);
                    response.put("columnTo", column);
                   
                    
                }
                gameClicked = 0;
                
            }
        }else if("Inventory".equalsIgnoreCase(tableType)){
            inventoryClicked++;
            if(inventoryClicked == 1){
                /*Ilyenkor kiválasztja a számot amivel mozogni szeretne.*/
                selectedInventoryIndex = column;/*itt megvan, hogy melyik elemet akarja mozgatni*/
                response.put("responseType", "InventorySelect");
                response.put("lineFrom", line);
                response.put("columnFrom", column);
            }else if(inventoryClicked == 2){
                /*Ilyenkor minden esetben nullára kell állítania az inventoryClicked változót, ugyanis
                megszünteti a kijelölést*/
                response.put("responseType", "StopSelection");
                response.put("lineFrom", line);
                response.put("columnFrom", column);
                inventoryClicked = 0;
            }
        }else{
            /*Gáz van*/
            response.put("responseType", "StopSelection");
            inventoryClicked = 0;
            gameClicked = 0;
        }
        
        return response;
    }
    
    
    
    
    public void setQuest(Player quest) {
        this.quest = quest;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    
    public Player getHost() {
        return host;
    }

    public Player getQuest() {
        return quest;
    }

    public Table getTable() {
        return table;
    }

    public void setHost(Player host) {
        this.host = host;
    }
}
