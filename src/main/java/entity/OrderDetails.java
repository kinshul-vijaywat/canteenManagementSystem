package entity;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDetails {

	private Integer orderId;
	private Integer customerId;
	private List<OrderFoodItem> foodItemsList;
	private Integer totalItems;
	private Double finalAmount;
	private LocalDateTime orderTime;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public List<OrderFoodItem> getFoodItemsList() {
		return foodItemsList;
	}
	public void setFoodItemsList(List<OrderFoodItem> foodItemsList) {
		this.foodItemsList = foodItemsList;
	}
	public Integer getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}
	public Double getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}
	public LocalDateTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}
	public OrderDetails(Integer orderId, Integer customerId, List<OrderFoodItem> foodItemsList, Integer totalItems,
			Double finalAmount, LocalDateTime orderTime) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.foodItemsList = foodItemsList;
		this.totalItems = totalItems;
		this.finalAmount = finalAmount;
		this.orderTime = orderTime;
	}
	public OrderDetails() {
		
	}
	
	
	
}
