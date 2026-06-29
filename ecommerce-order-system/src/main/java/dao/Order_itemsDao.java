package dao;
import java.util.List;

import entity.Order_Items;
public interface Order_itemsDao {
	
	//===================新增訂單明細===========================
	boolean insert(Order_Items order_Items);
	
	//===================查詢全部訂單明細=========================
	List<Order_Items> selectAll();
	List<Order_Items> selectByOrderId(Integer orderId);
	
	//===================刪除訂單明細===========================
	boolean deleteById(Integer id);
	
	
	

}
