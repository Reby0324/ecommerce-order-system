package dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.OrdersDao;
import entity.Orders;
import util.DbConnection;

public class OrdersDaoImpl implements OrdersDao {

	public static void main(String[] args) {
		//======================== 測試新增訂單 ========================
		/*Orders orders=new Orders();
		orders.setUserId(1);
		orders.setTotalPrice(new BigDecimal("1500.00"));
		orders.setStatus("已成立");
		
		int orderId=new OrdersDaoImpl().insert(orders);
		System.out.println("新增訂單ID :"+orderId);*/
		
		//======================== 測試查詢全部訂單 ========================
		/*List<Orders> list=new OrdersDaoImpl().selectAll();
		
		for(Orders o:list) {
			System.out.println(o.getId()+"\t"+o.getUserId() + "\t" +o.getTotalPrice() + "\t" +o.getStatus() + "\t" +o.getCreatedAt());
		}*/
		
		//======================== 測試依照使用者 ID 查詢歷史訂單 ========================
		/*List<Orders> list=new OrdersDaoImpl().selectByUserId(1);
		if(!list.isEmpty()) {
			Orders o=list.get(0);
			System.out.println(o.getId() + "\t" +o.getUserId() + "\t" +o.getTotalPrice() + "\t" +o.getStatus() + "\t" +o.getCreatedAt());
		}else {
			System.out.println("查無此訂單");
		}*/
		
		// ======================== 測試修改訂單狀態 ========================
		/*System.out.println(new OrdersDaoImpl().updateStatus(1, "已付款"));*/
		
		//======================== 測試刪除訂單 ========================
		System.out.println(new OrdersDaoImpl().deleteById(1));
		
		
				
	}
	Connection conn=DbConnection.getDb();
	@Override
	public int insert(Orders orders) {
		// 新增訂單，回傳訂單ID
		String sql="insert into orders(user_id,total_price,status) values(?,?,?)";
		int orderId=0;
		try { //這段是詢問AI:用來建立一個支援返回自動生成主鍵的預編譯 SQL 陳述式物件
			PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, orders.getUserId());
			ps.setBigDecimal(2, orders.getTotalPrice());
			ps.setString(3, orders.getStatus());
			
			int result=ps.executeUpdate();
			
			if(result>0) {
				ResultSet rs=ps.getGeneratedKeys();
				if(rs.next()) {
					orderId=rs.getInt(1);	
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderId;
	}

	@Override
	public List<Orders> selectAll() {
		String sql = "select * from orders order by id desc";
		List<Orders> list = new ArrayList<>();		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Orders orders = new Orders();
				orders.setId(rs.getInt("id"));
				orders.setUserId(rs.getInt("user_id"));
				orders.setTotalPrice(rs.getBigDecimal("total_price"));
				orders.setStatus(rs.getString("status"));
				orders.setCreatedAt(rs.getTimestamp("created_at"));
				
				list.add(orders);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
		}

	@Override
	public List<Orders> selectByUserId(Integer userId) {
		//測試依照訂單 ID 查詢
		String sql="select * from orders where id=?";
		List<Orders> list=new ArrayList<>();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				Orders orders = new Orders();

				orders.setId(rs.getInt("id"));
				orders.setUserId(rs.getInt("user_id"));
				orders.setTotalPrice(rs.getBigDecimal("total_price"));
				orders.setStatus(rs.getString("status"));
				orders.setCreatedAt(rs.getTimestamp("created_at"));

				list.add(orders);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean updateStatus(Integer id, String status) {
		// 修改訂單狀態
		String sql="update orders set status=? where id=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);
			
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// 刪除資料
		String sql="delete from orders where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			return ps.executeUpdate()>0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
