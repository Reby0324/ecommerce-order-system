package service;

import entity.Users;

public interface UsersService {
	
	//登入判斷 
	Users login(String username,String password);
	
	//註冊新使用者
	boolean register(Users users);
	
	//檢查帳號是否重複 true=帳號已存在 false=帳號可以使用
	boolean checkUsername(String username);

}
