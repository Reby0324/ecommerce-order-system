package entity;

import java.math.BigDecimal;

public class Products {
	private Integer id;
	private String product_name;
	private String category;
	private BigDecimal price;
	private Integer stock;
	private String description;
	
	
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Products(String product_name, String category, BigDecimal price, Integer stock, String description) {
		super();
		this.product_name = product_name;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.description = description;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
