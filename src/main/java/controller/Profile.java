package controller;
import java.io.IOException;
import java.util.List;

import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DatabaseUtils;


@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Profile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		 if(session!=null && session.getAttribute("customer_id") != null){  
			 String email = (String)session.getAttribute("customer_email");
			 DatabaseUtils db = new DatabaseUtils();
			 List<Customer> findCustomerByEmail = db.findCustomerByEmail(email);
			 request.setAttribute("profile", findCustomerByEmail.get(0));
			 db.closeConnection();
			 request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
		 }else {
			 request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
