package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Users implements Serializable{
	private int id;
	private String username;
	private String password;
	private String email;
	private String level;
	private BigDecimal balance;
	private Timestamp createdAt;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//建構式
	public Users(String username, String password, String email, String level, BigDecimal balance) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.level = level;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	

}
