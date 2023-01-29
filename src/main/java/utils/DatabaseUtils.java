package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Customer;
import entity.MenuFoodItems;
import entity.OrderDetails;
import entity.OrderFoodItem;

public class DatabaseUtils {
	
	Connection con;
	
	public DatabaseUtils() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_management?useSSL=false","root","root");			
		} catch (Exception e) {
			e.printStackTrace();
			con = null;
		}
	}
	
	public boolean createCustomer(Customer customer) {
		try {
			PreparedStatement pst = con.prepareStatement("INSERT INTO customers (customer_name, customer_email, mobile_number, address, password) VALUES (?,?,?,?,?)");
			pst.setString(1, customer.getCustomerName());
			pst.setString(2, customer.getCustomerEmail());
			pst.setString(3, customer.getMobileNumber());
			pst.setString(4, customer.getAddress());
			pst.setString(5, customer.getPassword());
			System.out.println(pst.toString());
			int rows = pst.executeUpdate();
			if(rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public boolean createOrder(OrderDetails order) {
		try {
			PreparedStatement pst = con.prepareStatement("INSERT INTO orders (customer_id, total_items, final_amount, order_time) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, order.getCustomerId());
			pst.setInt(2, order.getTotalItems());
			pst.setDouble(3, order.getFinalAmount());
			pst.setTimestamp(4, Timestamp.valueOf(order.getOrderTime()));
			System.out.println(pst.toString());
			pst.executeUpdate();
			ResultSet result = pst.getGeneratedKeys();
			if(result.next() && result != null){
				System.out.println(result.toString());
				int orderId = result.getInt(1);
				boolean foodAdded = createOrderFoodItem(order.getFoodItemsList(), orderId);
				return foodAdded;
			}			
			return false;			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public boolean createOrderFoodItem(List<OrderFoodItem> foodList,int orderId) {
		try {
			boolean ans = true;
			for(OrderFoodItem food : foodList) {
				PreparedStatement pst = con.prepareStatement("INSERT INTO order_food_items (order_id, food_name, food_price, food_quantity) VALUES (?,?,?,?)");
				pst.setInt(1, orderId);
				pst.setString(2, food.getFoodName());
				pst.setDouble(3, food.getFoodPrice());
				pst.setInt(4, food.getFoodQuantity());
				System.out.println(pst.toString());
				int rows = pst.executeUpdate();
				ans = ans & rows > 0;
			}
			return ans;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Customer> findCustomerByEmail(String email) {		
		List<Customer> cList = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM customers where customer_email = ?");
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt("customer_id"));
				customer.setCustomerName(rs.getString("customer_name"));
				customer.setCustomerEmail(rs.getString("customer_email"));
				customer.setAddress(rs.getString("address"));
				customer.setMobileNumber(rs.getString("mobile_number"));
				customer.setPassword(rs.getString("password"));
				customer.setPreviousOrders(findOrdersByCustomer(customer.getCustomerId()));
				cList.add(customer);
			}
			return cList;
		} catch (Exception e) {
			e.printStackTrace();
			return cList;
		}		
	}
	public List<OrderDetails> findOrdersByCustomer(int id) {		
		List<OrderDetails> oList = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM orders where customer_id = ? ORDER BY order_time desc");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				OrderDetails order = new OrderDetails();
				order.setOrderId(rs.getInt("order_id"));
				order.setCustomerId(rs.getInt("customer_id"));
				order.setTotalItems(rs.getInt("total_items"));
				order.setFinalAmount(rs.getDouble("final_amount"));
				order.setOrderTime(rs.getTimestamp("order_time").toLocalDateTime());
				order.setFoodItemsList(findFoodItemsByOrder(order.getOrderId()));
				oList.add(order);
			}
			return oList;
		} catch (Exception e) {
			e.printStackTrace();
			return oList;
		}		
	}
	
	public List<OrderFoodItem> findFoodItemsByOrder(int id) {		
		List<OrderFoodItem> fList = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM order_food_items where order_id = ?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				OrderFoodItem food = new OrderFoodItem();
				food.setOrderId(rs.getInt("order_id"));
				food.setFoodId(rs.getInt("food_item_id"));
				food.setFoodName(rs.getString("food_name"));
				food.setFoodPrice(rs.getDouble("food_price"));
				food.setFoodQuantity(rs.getInt("food_quantity"));
				fList.add(food);
			}
			return fList;
		} catch (Exception e) {
			e.printStackTrace();
			return fList;
		}		
	}
	
	public List<MenuFoodItems> getMenuItems(){
		List<MenuFoodItems> itemsList = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM menu_items");
			ResultSet rs = pst.executeQuery();			
			while(rs.next()) {
				MenuFoodItems foodItem = new MenuFoodItems();
				foodItem.setMenuId(rs.getInt("menu_id"));
				foodItem.setDisplayName(rs.getString("display_name"));
				foodItem.setMrp(rs.getDouble("mrp"));
				itemsList.add(foodItem);
			}
			return itemsList;
		} catch (Exception e) {
			e.printStackTrace();
			return itemsList;
		}		
	}
	
	public MenuFoodItems getMenuItemDetailsById(int id){
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM menu_items where menu_id = ?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();			
			while(rs.next()) {
				MenuFoodItems foodItem = new MenuFoodItems();
				foodItem.setMenuId(rs.getInt("menu_id"));
				foodItem.setDisplayName(rs.getString("display_name"));
				foodItem.setMrp(rs.getDouble("mrp"));
				return foodItem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
