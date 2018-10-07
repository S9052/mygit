package com.qf.dao;

import java.util.List;
import java.util.Map;

import com.qf.entity.User;

public interface IUserDao {

	public User findByNo(String no);
	
	public int count();
	
	public List<User> findAll(Map<String, Object> info);
	
	public User findById(Integer id);
	
	public void deleteById(Integer id);
	
	public void update(User user);
}
