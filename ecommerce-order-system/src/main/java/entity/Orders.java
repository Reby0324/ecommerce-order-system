package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Orders {
	private Integer id;
	private Integer userId;
	private BigDecimal totalPrice;
	private String status;
	private Timestamp createdAt;
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(Integer userId, BigDecimal totalPrice, String status) {
		super();
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.status = status;
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
	

}
