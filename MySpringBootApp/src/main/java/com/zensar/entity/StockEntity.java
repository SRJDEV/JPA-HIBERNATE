package com.zensar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "STOCKS")
public class StockEntity {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "stock_name")
	private String name;
	@Column(name = "stock_price")
	private int price;
	@Column(name = "stock_market")
	private String market;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public StockEntity(int id, String name, int price, String market) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.market = market;
	}

	public StockEntity() {
		super();
	}

	@Override
	public String toString() {
		return "StockEntity [id=" + id + ", name=" + name + ", price=" + price + ", market=" + market + "]";
	}

}
