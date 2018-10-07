package com.qf.dao;

import java.util.List;
import java.util.Map;

import com.qf.entity.Role;

public interface IRoleDao {
	
	public int count();
	
	public List<Role> findAll(Map<String, Object> info);
	
	public Role findById(Integer id);
	
	public void deleteById(Integer id);
	
	public List<Role> findAuthoById(Integer id);
	
	public List<Role> findAutho();
}
