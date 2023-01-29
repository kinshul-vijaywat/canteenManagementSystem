package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;


import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DatabaseUtils;
import utils.MyUtils;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Login() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		Map<String, String[]> parameterMap = request.getParameterMap();
		parameterMap.forEach((key, value) -> System.out.println(key + ":" + value));
		String password = MyUtils.md5(request.getParameter("password"));
		DatabaseUtils db = new DatabaseUtils();
		List<Customer> customerList = db.findCustomerByEmail(email);
		if(!customerList.isEmpty()) {
			String passwordFromDB = customerList.get(0).getPassword();
			if(passwordFromDB.equals(password)) {
				HttpSession session=request.getSession();  
		        session.setAttribute("customer_name",customerList.get(0).getCustomerName());
		        session.setAttribute("customer_email",customerList.get(0).getCustomerEmail());
		        session.setAttribute("customer_id",customerList.get(0).getCustomerId());
		        response.sendRedirect("home");
			}else {
				System.out.println("Incorrect Password");
				request.setAttribute("status", "failure");
				request.setAttribute("message", "Email or password is incorrect");
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			}
		}else {
			System.out.println("Incorrect Email");
			request.setAttribute("status", "failure");
			request.setAttribute("message", "Email or password is incorrect");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
		db.closeConnection();
	}

}

