package hu.astron.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import hu.mathgame.persist.Field;
import hu.mathgame.persist.GameDAO;
import hu.mathgame.persist.Table;
import hu.mathgame.persist.User;

@Stateless
@LocalBean
public class GameLogicBean {

    @EJB
    GameDAO dao;

    public GameLogicBean() {

    }

    public List<Table> allTables() {
        return dao.allTables();
    }

    public List<Field> tableFields(int tableID) {
        return dao.tableFields(tableID);
    }

    public List<User> tableUsers(int tableID) {
        return dao.tableUsers(tableID);
    }

}
