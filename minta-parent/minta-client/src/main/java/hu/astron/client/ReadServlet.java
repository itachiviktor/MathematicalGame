package hu.astron.client;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.astron.business.GameLogicBean;
import hu.mathgame.persist.Table;

@WebServlet("/ReadServlet")
public class ReadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private GameLogicBean bean;

    public ReadServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Table> list = bean.allTables();

        StringBuilder out = new StringBuilder();
        out.append("<!DOCTYPE html>");
        out.append("<html>");
        out.append("<head>");
        out.append("<title>List All Matches </title>");
        out.append("</head>");
        out.append("<body>");
        out.append("<table>");
        out.append("<tr><th>Date</th><th>Team 1</th><th>Team 2</th>")
                .append("<th>Half Time Score</th><th>Full Time Score</th></tr>");

        for (Table result : list) {
            out.append("<tr><td>");
            out.append(result.toString());
            int tableID = result.getId();
            for (int i = 0; i < bean.tableUsers(tableID).size(); i++) {
                out.append("<tr><td>");
                out.append(bean.tableUsers(tableID).get(i).toString());
            }
            for (int i = 0; i < bean.tableFields(tableID).size(); i++) {
                out.append("<tr><td>");
                out.append(bean.tableFields(tableID).get(i).toString());
            }

        }

        out.append("</body>");
        out.append("</html>");
        response.getWriter().write(out.toString());
        response.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
