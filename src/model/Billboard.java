package model;

import java.io.Serializable;

public class Billboard implements Serializable {

	private static final long serialVersionUID = 1L;
	private String width;
	private String height;
	private String inUse;
	private String brand;
	
	public Billboard(String width, String height, String inUse, String brand) {
		super();
		this.width = width;
		this.height = height;
		this.inUse = inUse;
		this.brand = brand;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String isInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public double calculateArea(double height, double width) {
		
		double area = height * width;
		return area;
	}
	
	@Override
	public String toString() {
		
		String line = 
				"\n\nWidth: " + getWidth() + 
				"\nHeight: " + getHeight() + 
				"\nIn use: " + isInUse() + 
				"\nBrand: " + getBrand();
		
		return line;
	}
}