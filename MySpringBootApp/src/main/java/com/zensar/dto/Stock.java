package com.zensar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Stock Model")
public class Stock {
	
	@ApiModelProperty("Unique Identifier for stock")
   private int id;
	@ApiModelProperty("Unique Name for stock")
	private String name;
	@ApiModelProperty("Unique Price for stock")
	private int price;
	@ApiModelProperty("Unique Market for stock")
	private String market;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Stock() {
		super();
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public Stock(int id, String name, int price, String market) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.market = market;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", price=" + price + ", market=" + market + "]";
	}

	
	
	

}
