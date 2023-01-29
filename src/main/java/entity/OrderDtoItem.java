package entity;

public class OrderDtoItem {
	
	private Integer foodId;
	private Integer foodQuantity;
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getFoodQuantity() {
		return foodQuantity;
	}
	public void setFoodQuantity(Integer foodQuantity) {
		this.foodQuantity = foodQuantity;
	}
	@Override
	public String toString() {
		return "OrderDtoItem [foodId=" + foodId + ", foodQuantity=" + foodQuantity + "]";
	}

}
