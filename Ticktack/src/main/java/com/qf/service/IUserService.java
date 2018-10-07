package com.qf.service;

import com.qf.entity.User;
import com.qf.vo.PageBean;

public interface IUserService {
	
	public User userLogin(String no, String password);
	
	public User findUserByNo(String no);
	
	public PageBean<User> findUsersByPage(Integer page, Integer limit, String name, Integer flag);
	
	public User findUserById(Integer id);
	
	public void deleteUserById(Integer id);
	
	public void updateUser(User user);
	
}
