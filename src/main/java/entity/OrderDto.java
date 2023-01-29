package entity;

import java.util.List;

public class OrderDto {
	
	private List<OrderDtoItem> foodItemsList;
	private Integer totalItems;
	public List<OrderDtoItem> getFoodItemsList() {
		return foodItemsList;
	}
	public void setFoodItemsList(List<OrderDtoItem> foodItemsList) {
		this.foodItemsList = foodItemsList;
	}
	public Integer getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}
	@Override
	public String toString() {
		return "OrderDto [foodItemsList=" + foodItemsList + ", totalItems=" + totalItems + "]";
	}
	
}
