package com.wellsfargo.fsd.ims.entity;

public class CartItem {

	
	String username;
	String title;
	int icode;
	int quantity;
	Double costPrice;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int geticode() {
		return icode;
	}

	public void seticode(int icode) {
		this.icode = icode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
