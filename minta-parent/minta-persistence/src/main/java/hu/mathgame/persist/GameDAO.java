package hu.mathgame.persist;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class GameDAO {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public GameDAO() {

    }

    public List<Table> allTables() {
        return em.createNamedQuery("Table.findAll", Table.class).getResultList();
    }

    public List<Field> tableFields(int tableID) {

        Table table = em.find(Table.class, tableID);
        return table.getFields();
    }

    public List<User> tableUsers(int tableID) {
        Table table = em.find(Table.class, tableID);
        return table.getUsers();
    }
}
