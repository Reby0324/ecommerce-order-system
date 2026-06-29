package dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Order_itemsDao;
import entity.Order_Items;
import util.DbConnection;

public class Order_itemsDaoImpl implements Order_itemsDao {

	public static void main(String[] args) {
		//======================== 測試新增訂單明細 ========================
		/*Order_Items item=new Order_Items(1,2,3,new BigDecimal("680.00"),new BigDecimal("1360.00"));
		System.out.println(new Order_itemsDaoImpl().insert(item));*/
		
		//======================== 測試查詢全部訂單明細 ========================
		/*List<Order_Items> list=new Order_itemsDaoImpl().selectAll();
		for(Order_Items item:list) {
			System.out.println(item.getId() + "\t" +
					item.getOrderId() + "\t" +
					item.getProductId() + "\t" +
					item.getQuantity() + "\t" +
					item.getPrice() + "\t" +
					item.getSubtotal());*/
		
		//======================== 測試查詢訂單ID明細 ========================
		//這裡是查order_id不是id
		/*List<Order_Items> List=new Order_itemsDaoImpl().selectByOrderId(2);
		for(Order_Items item:List) {
			System.out.println(item.getId() + "\t" +
					item.getOrderId() + "\t" +
					item.getProductId() + "\t" +
					item.getQuantity() + "\t" +
					item.getPrice() + "\t" +
					item.getSubtotal());*/
			
		//======================== 測試刪除訂單明細 ========================	
			System.out.println(new Order_itemsDaoImpl().deleteById(2));
		
			
	}
	Connection conn=DbConnection.getDb();
	@Override
	public boolean insert(Order_Items order_Items) {
		//新增訂單明細
		String sql="insert into order_items(order_id,product_id,quantity,price,subtotal) values(?,?,?,?,?)";
		try {
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setInt(1, order_Items.getOrderId());
			ps.setInt(2, order_Items.getProductId());
			ps.setInt(3, order_Items.getQuantity());
			ps.setBigDecimal(4, order_Items.getPrice());
			ps.setBigDecimal(5, order_Items.getSubtotal());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Order_Items> selectAll() {
		// 查詢全部
		String sql="select * from order_items order by id desc";
		List<Order_Items>list=new ArrayList<>();
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					Order_Items item = new Order_Items();

					item.setId(rs.getInt("id"));
					item.setOrderId(rs.getInt("order_id"));
					item.setProductId(rs.getInt("product_id"));
					item.setQuantity(rs.getInt("quantity"));
					item.setPrice(rs.getBigDecimal("price"));
					item.setSubtotal(rs.getBigDecimal("subtotal"));

					list.add(item);
				}
				
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		
	}

	@Override
	public List<Order_Items> selectByOrderId(Integer orderId) {
		// 依照ID查詢訂單明細
		String sql="select * from order_items where order_id=? order by id desc";
		List<Order_Items>list=new ArrayList<>();
		
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, orderId);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Order_Items item = new Order_Items();

					item.setId(rs.getInt("id"));
					item.setOrderId(rs.getInt("order_id"));
					item.setProductId(rs.getInt("product_id"));
					item.setQuantity(rs.getInt("quantity"));
					item.setPrice(rs.getBigDecimal("price"));
					item.setSubtotal(rs.getBigDecimal("subtotal"));

					list.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
	
			
	}

	@Override
	public boolean deleteById(Integer id) {
		//刪除訂單明細
		String sql="delete from order_items where id=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
