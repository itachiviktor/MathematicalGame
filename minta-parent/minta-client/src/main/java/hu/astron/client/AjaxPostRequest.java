package hu.astron.client;

import java.io.BufferedReader;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


import beans.GameBean;

@WebServlet(name = "AjaxPostRequest", urlPatterns = {"/AjaxPostRequest"})
public class AjaxPostRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    private GameBean gameBean;
	
    public AjaxPostRequest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /*StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;
        while( (str = br.readLine()) != null ){
            sb.append(str);
        }    
        
        JSONObject jObj = new JSONObject(sb.toString());
     

        Integer v = (Integer)jObj.get("value");
        JSONArray arr = new JSONArray();
        
        Table table = new Table(10, 10);
        modell.Number n = new modell.Number(v);
        n.setLine((Integer)jObj.get("line"));
        n.setColumn((Integer)jObj.get("column"));
        System.out.println("line" + n.getLine() + "column: " + n.getColumn());
        List<Field> list = table.whereCanNumberMove(n);
        
        
        for(int i=0;i<list.size();i++){
            JSONObject obj = new JSONObject();
            obj.append("line", list.get(i).getLine());
            obj.append("column", list.get(i).getColumn());
            arr.put(obj);
        }
        response.getWriter().write(arr.toString());*/
         StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;
        while( (str = br.readLine()) != null ){
            sb.append(str);
        }    
        
        JSONObject jObj = new JSONObject(sb.toString());
        
        JSONObject res = gameBean.calculateGameState(jObj);
       
        response.getWriter().write(res.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
