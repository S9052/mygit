package com.qf.service;

import java.util.List;

import com.qf.entity.Role;
import com.qf.vo.PageBean;

public interface IRoleService {
	public PageBean<Role> findAllRole(Integer page, Integer limit, String name);
	
	public Role findRoleById(Integer id);
	
	public void deleteRoleById(Integer id);

	public List<Role> findAuthoById(Integer id);
	
	public List<Role> findAutho();
}
