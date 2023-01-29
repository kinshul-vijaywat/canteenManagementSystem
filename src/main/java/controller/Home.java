package controller;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DatabaseUtils;


@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Home() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		 if(session!=null && session.getAttribute("customer_id") != null){  
			 DatabaseUtils db = new DatabaseUtils();
			 request.setAttribute("menu", db.getMenuItems());
			 db.closeConnection();
			 request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
		 }else {
			 request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
