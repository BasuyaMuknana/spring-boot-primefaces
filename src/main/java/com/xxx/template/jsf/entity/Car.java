package com.xxx.template.jsf.entity;


public class Car {
	public String id;
	public String brand;
	public String color;
	public int year;
	public int miles;
	public boolean isSold;

	public Car(String id, String brand, int year, String color, int miles, boolean isSold) {
		this.id = id;
		this.brand = brand;
		this.year = year;
		this.color = color;
		this.miles = miles;
		this.isSold = isSold;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

	public boolean isSold() {
		return isSold;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

}
