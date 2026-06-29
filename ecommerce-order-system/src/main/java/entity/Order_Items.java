package entity;

import java.math.BigDecimal;

public class Order_Items {
	private Integer id;
	private Integer orderId;
	private Integer productId;
	private Integer quantity;
	private BigDecimal price;
	private BigDecimal subtotal;
	
	public Order_Items() {
		super();
		// TODO Auto-generated constructor stub
		
		
	}

	public Order_Items(Integer orderId, Integer productId, Integer quantity, BigDecimal price, BigDecimal subtotal) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.subtotal = subtotal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
	


}
