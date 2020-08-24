package com.wellsfargo.fsd.ims.entity;

import java.time.LocalDate;

public class Item {

	private Integer icode;
	private String title;
	//private LocalDate packageDate;
	//private Boolean fragile;
	private String unit;
	private Double costPrice;
	//private Double sellingPrice;
	
	//private String firstname;
	//private String email;
	//private String deliveryaddress;
	
	public Item() {
		//left unimplemente
	}

	public Item(Integer icode, String title, String unit, Double costPrice) {
		super();
		this.icode = icode;
		this.title = title;
		this.unit = unit;
		this.costPrice = costPrice;
		
	}

	public Integer geticode() {
		return icode;
	}

	public void seticode(Integer icode) {
		this.icode = icode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}


	@Override
	public String toString() {
		return "Item [icode=" + icode + ", title=" + title + 
				 ", unit=" + unit + ", costPrice=" + costPrice +"]";
	}
}
