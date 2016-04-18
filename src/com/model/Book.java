package com.model;

public class Book {
	private String name;
	private String id;
	private String price;
	
	public Book(String id,String name,String price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String toString() {
		return id + " " + name + " " + price;
	}
	
}
