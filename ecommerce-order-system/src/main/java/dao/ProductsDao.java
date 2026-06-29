package dao;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import entity.Products;

public interface ProductsDao {

	
	//=====================前台購物功能====================
	//查詢全部商品
	List<Products> findAll();
	
	//依照商品ID
	List<Products> findById(Integer id);
	
	//查詢商品轉成JTable表格
	DefaultTableModel findAllProductsTable();
	
	//=====================後台商品管理CRUD====================
	//新增
	boolean add(Products products);
	
	//修改
	boolean update(Products products);
	
	//刪除
	boolean deleteById(Integer is);
	
	//=====================結帳交易====================
	//結帳時扣除商品庫存
	//amount=商品數量
	boolean updateStock(Integer id,Integer amount);
 
	
}
