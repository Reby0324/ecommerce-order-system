package dao; //寫SQL方法名稱

import java.math.BigDecimal;
import java.util.List;

import entity.Users;

public interface UsersDao {
	
	//========================基礎會員功能=========================
	
	//create
	//註冊新使用者
	boolean register(Users user); 
	
	
	//read
	//登入驗證
    Users login(String username,String password); 
	//依照帳號查詢使用者，通常用於註冊時檢查帳號是否存在
	Users findByUsername(String name);
	//依照會員ID，查詢單一會員資料
	List<Users> findById(Integer id);
	//查詢所有使用者
	List<Users> findAll();
	
	
	//update	
	//修改使用者資料
	boolean update(Users user); 
	//修改使用者等級
	boolean updateLevel(Integer id,String level);	
	//儲值-增加餘額
	boolean addBalance(Integer id,BigDecimal amount);	
	//結帳-扣除餘額
	boolean deductBalance(Integer id,BigDecimal amount);
	
	
	//delete
	//刪除使用者
	boolean deleteById(int id);
	
	
	
	

	
	
	

}
