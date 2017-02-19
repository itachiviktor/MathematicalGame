/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.astron.client;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import duel.DuelBean;
import modell.Duel;
import modell.Inventory;
import modell.Player;

/**
 *
 * @author Kiss
 */
@WebServlet(name = "SearchDuelPartner", urlPatterns = {"/SearchDuelPartner"})
public class SearchDuelPartner extends HttpServlet {
    @EJB
    private DuelBean duelBean;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchDuelPartner</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchDuelPartner at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("user");
        Player me = new Player(40, new Inventory(), username);
        
        duelBean.login(me);
        
        boolean ok = false;
        
        response.setContentType("application/json");
        for(int i=0;i<15;i++){
            Duel duel = duelBean.hasMeAnyDuelPartner(me);
            if(duel != null){
                
                JSONObject json = new JSONObject();
                json.put("player1", duel.getPlayer1().getUsername());
                json.put("player2", duel.getPlayer2().getUsername());
                
                response.getWriter().write(json.toString());
                ok = true;
                response.getWriter().flush();
                break;
            }else{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SearchDuelPartner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        duelBean.removeMeFromWaiters(me);
        
        if(!ok){
            
            JSONObject ob = new JSONObject();
            ob.put("error", "no enemy");
            
            response.getWriter().write(ob.toString());
        }   
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
