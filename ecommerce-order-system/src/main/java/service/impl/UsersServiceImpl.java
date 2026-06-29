package service.impl;

import java.math.BigDecimal;

import dao.UsersDao;
import dao.impl.UsersDaoImpl;
import entity.Users;
import service.UsersService;

public class UsersServiceImpl implements UsersService {

	public static void main(String[] args) {
		//=======================測試登入帳號密碼=====================
		/*System.out.println(new UsersServiceImpl().login("teacher", "1234"));*/
		
		//=======================測試檢查帳號是否重複=====================
		/*System.out.println(new UsersServiceImpl().checkUsername("teacher"));*/
		
		//=======================測試註冊新增使用者=====================
		Users users=new Users(
				"Angela",
				"1234",
				"Angela@gmail.com",
				"Normal",
				new BigDecimal("1000.00"));
		
		System.out.println(new UsersServiceImpl().register(users));

	}
	
	UsersDao udi=new UsersDaoImpl();

	@Override
	public Users login(String username, String password) {
		// 登入判斷
		return udi.login(username,password);
	}

	@Override
	public boolean checkUsername(String username) {
		// 註冊檢查帳號是否重複
		boolean x=false;
		Users users=udi.findByUsername(username);
		
		if(users !=null) {
			x=true;
		}
		return x;
	}
	
	@Override
	public boolean register(Users users) {
		//註冊新增使用者
		if (checkUsername(users.getUsername())) {
		System.out.println("帳號已存在，請重新註冊");
		return false;
				}
				
		//等級預設為Normal
		if(users.getLevel() == null || users.getLevel().isEmpty()) {
			users.setLevel("Normal");
		}
		
		//金額預設給1000
		if(users.getBalance() == null) {
			users.setBalance(new BigDecimal("1000.00"));
		}
		return udi.register(users);
		
	}


}
