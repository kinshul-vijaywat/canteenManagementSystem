package controller;

import java.io.IOException;
import java.util.List;

import utils.DatabaseUtils;
import utils.MyUtils;
import entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		 request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = MyUtils.md5(request.getParameter("password"));
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		boolean isformComplete = true;
		if(MyUtils.isEmptyString(name) || MyUtils.isEmptyString(email) || MyUtils.isEmptyString(password) || MyUtils.isEmptyString(address) || MyUtils.isEmptyString(contact)) {
			isformComplete = false;
		}
		if(isformComplete) {
			DatabaseUtils db = new DatabaseUtils();
			List<Customer> findCustomerByEmail = db.findCustomerByEmail(email);
			if(findCustomerByEmail.isEmpty()) {
				Customer customer = new Customer();
				customer.setAddress(address);
				customer.setCustomerEmail(email);
				customer.setCustomerName(name);
				customer.setMobileNumber(contact);
				customer.setPassword(password);
				boolean createPassenger = db.createCustomer(customer);
				if(createPassenger) {
					request.setAttribute("status", "success");
					request.setAttribute("message", "Customer Added Successfully");
					request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("status", "failure");
				request.setAttribute("message", "Customer with Provided Email already exists");
				request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
			}
			db.closeConnection();			
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

