package dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.ProductsDao;
import entity.Products;
import util.DbConnection;

public class ProductsDaoImpl implements ProductsDao{	
	
		public static void main(String[] args) {
			// ======================== 測試新增商品 ========================
			/*Products products=new Products("普通心理學","心理學書籍",new BigDecimal("680.00"),20,"適合心理所考試");
			System.out.println(new ProductsDaoImpl().add(products));*/
			// ======================== 測試查詢商品 ========================
			/*List<Products> list=new ProductsDaoImpl().findAll();
			for(Products p:list) {
			System.out.println(p.getId()+"\t"+p.getProduct_name()+"t"+p.getCategory()+"\t"+p.getPrice()+"\t"+p.getStock()+"\t"+p.getDescription());
			}*/
			// ======================== 測試依照id查詢商品 ========================
			/*List<Products> list=new ProductsDaoImpl().findById(1);
			//!=NOT 清單不是空的（代表清單裡面有包含至少一個元素）
			if( !list.isEmpty()) {
				Products p=list.get(0);
				System.out.println(p.getId()+"\t"+p.getProduct_name()+"\t"+p.getCategory()+"\t"+p.getPrice()+"\t"+p.getStock()+"\t"+p.getDescription());
			}else {
				System.out.println("查無此商品");
			}*/
			// ======================== 測試修改商品 ========================
			/*List<Products> list=new ProductsDaoImpl().findById(1);
			if( !list.isEmpty()) {
				Products p= list.get(0);		
				p.setPrice(new BigDecimal(699.00));
				p.setStock(30);
				System.out.println(new ProductsDaoImpl().update(p));
			}*/
			// ======================== 測試刪除商品 ========================
			/*System.out.println(new ProductsDaoImpl().deleteById(4));*/
			
			// ======================== 測試扣除商品庫存 ========================
			System.out.println(new ProductsDaoImpl().updateStock(1,2));//()代表id=1扣2個
		}
		
	Connection conn=DbConnection.getDb();
	
	@Override
	public List<Products> findAll() {
		//查詢全部商品 
		String sql="select *from products order by id desc"; //order by desc是進行排序
		List<Products> list=new ArrayList<>();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {			
				Products products=new Products();
				products.setId(rs.getInt("id"));
				products.setProduct_name(rs.getString("product_name"));
				products.setCategory(rs.getString("category"));
				products.setPrice(rs.getBigDecimal("price"));
				products.setStock(rs.getInt("stock"));
				products.setDescription(rs.getString("description"));
				
				list.add(products);
			}					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return list;	
	}

	@Override
	public List<Products> findById(Integer id) {
		//依照商品ID查詢商品
		String sql="select * from products where id=?";
		List<Products> list=new ArrayList<>();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id); //這個一定要放這行
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Products products =new Products();
				products.setId(rs.getInt("id"));
				products.setProduct_name(rs.getString("product_name"));
				products.setCategory(rs.getString("category"));
				products.setPrice(rs.getBigDecimal("price"));
				products.setDescription(rs.getString("description"));
				
				list.add(products);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean add(Products products) {
		// 新增商品
		String sql="insert into products(product_name,category,price,stock,description,created_at) values(?,?,?,?,?,now())";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, products.getProduct_name());
			ps.setString(2, products.getCategory());
			ps.setBigDecimal(3, products.getPrice());
			ps.setInt(4, products.getStock());
			ps.setString(5, products.getDescription());
			
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Products products) {
		String sql = "update products set product_name=?, category=?, price=?, stock=?, description=? where id=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, products.getProduct_name());
			ps.setString(2, products.getCategory());
			ps.setBigDecimal(3, products.getPrice());
			ps.setInt(4, products.getStock());
			ps.setString(5, products.getDescription());
			ps.setInt(6, products.getId());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		//刪除商品
		String sql="delete from products where id=?";
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

	@Override
	public boolean updateStock(Integer id, Integer amount) {
		//結帳扣除商品庫存
		//amoutn=購買數量
		String sql="update products set stock=stock-? where id=? and stock>=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setInt(2, id);
			ps.setInt(3, amount);
			
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public DefaultTableModel findAllProductsTable() {
		// 建立JTable欄位標題
		List<Products> list=findAll();
		DefaultTableModel model=new DefaultTableModel();
		
		model.addColumn("編號");
		model.addColumn("商品名稱");
		model.addColumn("分類");
		model.addColumn("價格");
		model.addColumn("庫存");
		model.addColumn("商品描述");
		
		for(Products p:list) {
			Object[] row=new Object[] {
					p.getId(),
					p.getProduct_name(),
					p.getCategory(),
					p.getPrice(),
					p.getStock(),
					p.getDescription()
					
			};
			
			model.addRow(row);
			
		}
		return model;
	}

}
