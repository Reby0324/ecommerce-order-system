package dao;

import java.util.List;

import entity.Orders;

public interface OrdersDao {
	//=====================查詢全部訂單=========================
	int insert(Orders orders);
	
	
	//=====================依照訂單ID查詢=========================
	List<Orders> selectAll();
	//=====================依照使用者ID查詢=========================
	List<Orders> selectByUserId(Integer userId);
	
	
	//=====================修改訂單狀態=========================
	boolean updateStatus(Integer id,String status);
	
	
	//=====================刪除訂單=========================
	boolean deleteById(Integer id);

}
