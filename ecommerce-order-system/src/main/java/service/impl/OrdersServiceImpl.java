package service.impl;

import java.math.BigDecimal;
import java.util.List;

import dao.OrdersDao;
import dao.Order_itemsDao;
import dao.impl.OrdersDaoImpl;
import dao.impl.Order_itemsDaoImpl;
import entity.Order_Items;
import entity.Orders;
import service.OrdersService;

public class OrdersServiceImpl implements OrdersService {
	
	OrdersDao odi = new OrdersDaoImpl();
	Order_itemsDao oidi = new Order_itemsDaoImpl();
	
	public static void main(String[] args) {
		
		// ======================== 測試新增訂單 ========================
		
		/*Orders orders = new Orders();
		orders.setUserId(1);
		orders.setTotalPrice(new BigDecimal("1360.00"));
		orders.setStatus("已成立");

		int orderId = new OrdersServiceImpl().createOrder(orders);
		System.out.println("新增訂單ID：" + orderId);*/
		// ======================== 測試新增訂單明細 ========================	
		/*Order_Items item = new Order_Items();
		item.setOrderId(1);
		item.setProductId(2);
		item.setQuantity(2);
		item.setPrice(new BigDecimal("780.00"));
		item.setSubtotal(new BigDecimal("1460.00"));

		System.out.println(new OrdersServiceImpl().createOrderItem(item));*/	
		// ======================== 測試查詢全部訂單 ========================	
		/*List<Orders> list = new OrdersServiceImpl().findAll();

		for (Orders o : list) {
			System.out.println(
					o.getId() + "\t" +
					o.getUserId() + "\t" +
					o.getTotalPrice() + "\t" +
					o.getStatus() + "\t" +
					o.getCreatedAt());
		}*/

		// ======================== 測試查詢某使用者歷史訂單 ========================
		
		/*List<Orders> list = new OrdersServiceImpl().findByUserId(2);

		for (Orders o : list) {
			System.out.println(
					o.getId() + "\t" +
					o.getUserId() + "\t" +
					o.getTotalPrice() + "\t" +
					o.getStatus() + "\t" +
					o.getCreatedAt()
			);
		}*/
		
		
		
		// ======================== 測試查詢訂單明細 ========================
		/*List<Order_Items> list = new OrdersServiceImpl().findItemsByOrderId(1);

		for (Order_Items item : list) {
			System.out.println(
					item.getId() + "\t" +
					item.getOrderId() + "\t" +
					item.getProductId() + "\t" +
					item.getQuantity() + "\t" +
					item.getPrice() + "\t" +
					item.getSubtotal()
			);
		}*/
		// ======================== 測試修改訂單狀態 ========================
		/*System.out.println(new OrdersServiceImpl().updateStatus(2, "已付款"));*/
	
		// ======================== 測試刪除訂單 ========================
		/*System.out.println(new OrdersServiceImpl().deleteById(4));*/
	}

	
	@Override
	public int createOrder(Orders orders) {
		// 新增訂單主檔
		return odi.insert(orders);
	}

	
	@Override
	public boolean createOrderItem(Order_Items orderItems) {
		// 新增訂單明細
		return oidi.insert(orderItems);
	}

	
	@Override
	public List<Orders> findAll() {
		// 查詢全部訂單
		return odi.selectAll();
	}

	
	@Override
	public List<Orders> findById(Integer id) {
		// 依照訂單 ID 查詢
		return odi.selectByUserId(id);
	}

	
	@Override
	public List<Orders> findByUserId(Integer userId) {
		// 依照使用者 ID 查詢歷史訂單
		return odi.selectByUserId(userId);
	}

	
	@Override
	public List<Order_Items> findItemsByOrderId(Integer orderId) {
		// 依照訂單 ID 查詢訂單明細
		return oidi.selectByOrderId(orderId);
	}

	
	@Override
	public boolean updateStatus(Integer id, String status) {
		// 修改訂單狀態
		return odi.updateStatus(id, status);
	}

	
	@Override
	public boolean deleteById(Integer id) {
		// 刪除訂單
		return odi.deleteById(id);
	}
}