package com.myproject.productrecommendor.model;

/**
 * @author NKHarish
 *	This is a Model Object for Product.
 */
public class Product {
	/**
	 * Variables which represent the properties of Product. 
	 */
	private int itemId;/*Unique Identifier for Product. 8 digit number.*/
	private int parentItemId;/*Parent Item Identifier for Product. 8 digit number.*/
	private String name;/* Name of the Product.*/
	private long upc;/* UPC of the Product.*/
	private String categoryPath; /* Category Path for the Product.*/
	private String productUrl; /* Unique URL for the Product.*/
	private float averageOverallRating; /* Average Overall Rating for the Product.*/
	
	/**
	 * Getters and Setters for the Product Class variables.
	 * 
	 */
	public float getAverageOverallRating() {
		return averageOverallRating;
	}
	public void setAverageOverallRating(float averageOverallRating) {
		this.averageOverallRating = averageOverallRating;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getParentItemId() {
		return parentItemId;
	}
	public void setParentItemId(int parentItemId) {
		this.parentItemId = parentItemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getUpc() {
		return upc;
	}
	public void setUpc(long upc) {
		this.upc = upc;
	}
	public String getCategoryPath() {
		return categoryPath;
	}
	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

}
