package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import entity.MenuFoodItems;
import entity.OrderDetails;
import entity.OrderDto;
import entity.OrderFoodItem;
import entity.OrderResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DatabaseUtils;

@WebServlet("/order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Order() {
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
		HttpSession session = request.getSession(false);
		 if(session!=null && session.getAttribute("customer_id") != null){  
			 System.out.println("I am in order Post");
				StringBuffer jb = new StringBuffer();
				  String line = null;
				  try {
				    BufferedReader reader = request.getReader();
				    while ((line = reader.readLine()) != null)
				      jb.append(line);
				  } catch (Exception e) { 
					  e.printStackTrace();
				  }
				  
				  Gson gson = new Gson();
				  OrderDto orderDto = gson.fromJson(jb.toString(), OrderDto.class);
				  System.out.println(orderDto.toString());
				  
				  DatabaseUtils db = new DatabaseUtils();
				  OrderDetails orderDetails = new OrderDetails();
				  List<OrderFoodItem> foodItemsList = new ArrayList<>();
				  Double totalPrice = 0.0;
				  for(int i = 0; i < orderDto.getTotalItems();i++) {
					  OrderFoodItem foodItem = new OrderFoodItem();
					  MenuFoodItems menuItemDetailsById = db.getMenuItemDetailsById(orderDto.getFoodItemsList().get(i).getFoodId());
					  foodItem.setFoodId(menuItemDetailsById.getMenuId());
					  foodItem.setFoodPrice(menuItemDetailsById.getMrp());
					  foodItem.setFoodName(menuItemDetailsById.getDisplayName());
					  foodItem.setFoodQuantity(orderDto.getFoodItemsList().get(i).getFoodQuantity());
					  totalPrice += foodItem.getFoodPrice() * foodItem.getFoodQuantity();
					  foodItemsList.add(foodItem);
				  }
				  orderDetails.setFoodItemsList(foodItemsList);
				  orderDetails.setCustomerId((int)session.getAttribute("customer_id"));
				  orderDetails.setTotalItems(foodItemsList.size());
				  orderDetails.setFinalAmount(totalPrice);
				  orderDetails.setOrderTime(LocalDateTime.now());
				  boolean createOrder = db.createOrder(orderDetails);
				  db.closeConnection();
				  String json = "";
				  if(createOrder) {
					  	json = new Gson().toJson(new OrderResponse("Order Created Successfully", Boolean.TRUE));
					    
				  }else {
					  	json = new Gson().toJson(new OrderResponse("Order Creation Error", Boolean.FALSE));				
				  }
				  response.setContentType("application/json");
				  response.setCharacterEncoding("UTF-8");
				  response.getWriter().write(json);
		 }else {
			 request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		 }
		
		  
	}

}