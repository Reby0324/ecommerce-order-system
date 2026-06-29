package dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UsersDao;
import entity.Users;
import util.DbConnection;

public class UsersDaoImpl implements UsersDao {
	
	public static void main(String[] args) {
		//=======================測試register註冊功能======================
		/*Users users=new Users(
				"test",
				"0000",
				"test@gmail.com",
				"Member",
				new BigDecimal("2000.00"));
		
		System.out.println(new UsersDaoImpl().register(users));*/
		
		//=========================測試login登入==========================
		/*Users users=new UsersDaoImpl().login("rosie","1234");
		if(users != null) {
			System.out.println("帳號:"+users.getUsername());
			System.out.println("等級:"+users.getLevel());
			System.out.println("餘額:"+users.getBalance());
		}else {
			System.out.println("登入失敗");
		}*/
		
		//========================測試帳號查詢==============================
		/*System.out.println(new UsersDaoImpl().findByUsername("rrrr"));*/
		
		//===========================測試ID查詢============================
		/*List<Users> list=new UsersDaoImpl().findById(1);
		Users users=list.get(0);
		System.out.println(users.getId()+
				"\t"+users.getUsername()+
				"\t"+users.getPassword()+
				"\t"+users.getEmail()+
				"\t"+users.getLevel()+
				"\t"+users.getBalance()+
				"\t"+users.getCreatedAt());*/
		
		//========================測試查詢全部使用者=============================
		/*List<Users> list=new UsersDaoImpl().findAll();
		for(Users u:list){
		System.out.println(u.getId()+
				"\t"+u.getUsername()+
				"\t"+u.getPassword()+
				"\t"+u.getEmail()+
				"\t"+u.getLevel()+
				"\t"+u.getBalance()+
				"\t"+u.getCreatedAt());
				}*/
		//=======================測試修改使用者===============================
		/*Users users=new UsersDaoImpl().findById(4).get(0); 
		users.setUsername("Angela");
		users.setPassword("1111");
		users.setEmail("Angela@gmail.com");
		users.setLevel("ADMIN");
		users.setBalance(new BigDecimal("1500.00"));
		
		/*System.out.println(new UsersDaoImpl().update(users));*/
		
		//=============================測試刪除使用者=========================
		//System.out.println(new UsersDaoImpl().deleteById(5));
		
	}
	
	Connection conn=DbConnection.getDb();

	@Override 
	public boolean register(Users users) {
		// 註冊新使用者
		String sql="insert into users(username,password,email,level,balance,created_at) values(?,?,?,?,?,now())";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, users.getUsername());
			ps.setString(2, users.getPassword());
			ps.setString(3, users.getEmail());
			ps.setString(4, users.getLevel());
			ps.setBigDecimal(5, users.getBalance());
			
			
			//如果成功新增一筆使用者資料，就回傳 true；如果沒有新增成功，就回傳 false
			return ps.executeUpdate() >0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Users login(String username, String password) {
		// 登入驗證
		Users users=null;
		String sql="select * from users where username=? and password=?";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery(); //執行查詢
			
			if(rs.next()){
				users=new Users();
				users.setId(rs.getInt("ID"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setEmail(rs.getString("email"));
				users.setLevel(rs.getString("level"));
				users.setBalance(rs.getBigDecimal("Balance"));
				users.setCreatedAt(rs.getTimestamp("created_at"));
		} 
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	
	@Override
	public Users findByUsername(String username) {
		// 依照帳號查尋使用者
		Users users=null;
		String sql="select * from users where username=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				users=new Users();
				users.setId(rs.getInt("id"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setEmail(rs.getString("email"));
				users.setLevel(rs.getString("level"));
				users.setBalance(rs.getBigDecimal("balance"));
				users.setCreatedAt(rs.getTimestamp("created_at"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<Users> findById(Integer id) {
		// 依照ID查詢使用者
		String sql="select * from Users where ID=?";
		List<Users> l=new ArrayList<>();
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				Users users=new Users();
				users.setId(rs.getInt("id"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setEmail(rs.getString("email"));
				users.setLevel(rs.getString("level"));
				users.setBalance(rs.getBigDecimal("balance"));
				users.setCreatedAt(rs.getTimestamp("created_at"));
				
				l.add(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return l;
	}

	@Override
	public List<Users> findAll() {
		// 查詢全部使用者
		String sql="select * from Users";
		List<Users> l=new ArrayList<>();	
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery(sql);
			
			while(rs.next())
			{
				Users users=new Users();
				users.setId(rs.getInt("id"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setEmail(rs.getString("email"));
				users.setLevel(rs.getString("level"));
				users.setBalance(rs.getBigDecimal("balance"));
				users.setCreatedAt(rs.getTimestamp("created_at"));
				
				l.add(users);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}

	@Override
	public boolean update(Users users) {
		// 修改使用者
		String sql="update users set username=?,password=?,email=?,level=?,balance=? where ID=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, users.getUsername());
			ps.setString(2,users.getPassword());
			ps.setString(3, users.getEmail());
			ps.setString(4, users.getLevel());
			ps.setBigDecimal(5,users.getBalance() );
			ps.setInt(6, users.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateLevel(Integer id, String level) {
		// 修改使用者等級
		return false;
	}

	@Override
	public boolean addBalance(Integer id, BigDecimal amount) {
		// 儲值-增加餘額
		return false;
	}

	@Override
	public boolean deductBalance(Integer id, BigDecimal amount) {
		// 結帳-扣款餘額
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// 刪除使用者
		String sql="delete from users where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		return false;
	}
}
