package com.qf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.dao.IRoleDao;
import com.qf.entity.Role;
import com.qf.service.IRoleService;
import com.qf.vo.PageBean;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleDao roleDao;

	@Override
	public PageBean<Role> findAllRole(Integer page, Integer limit, String name) {
		// TODO Auto-generated method stub
		
		PageBean<Role> pageInfo = new PageBean<>();
		
		pageInfo.setCurrentPage(page);
		
		// 设置每页显示的记录数
		pageInfo.setPageSize(limit);
		
		// 获取表中的记录总数
		int count = roleDao.count();
		// 设置总记录数
		pageInfo.setCount(count);
		// 计算总页数
		if(count % pageInfo.getPageSize() == 0){
			pageInfo.setTotalPage(count / pageInfo.getPageSize());
		}else{
			pageInfo.setTotalPage(count / pageInfo.getPageSize()+ 1);
		}
		// 根据页码计算分页查询时的开始索引
		int index = (page - 1) * pageInfo.getPageSize();
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		map.put("name", name);
		List<Role> list = roleDao.findAll(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public Role findRoleById(Integer id) {
		// TODO Auto-generated method stub
		Role r = null;
		
		try {
			r = roleDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		return r;
	}

	@Override
	public void deleteRoleById(Integer id) {
		// TODO Auto-generated method stub
		
		try {
			Role role = roleDao.findById(id);
			if(role != null) {
				roleDao.deleteById(id);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Role> findAuthoById(Integer id) {
		// TODO Auto-generated method stub
		List<Role> list = null;
		Role role = roleDao.findById(id);
		try {
			if(role != null) {
				list = ((IRoleDao) roleDao).findAuthoById(id);
			} else {
				throw new RuntimeException("NO WAY !!!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	@Override
	public List<Role> findAutho() {
		// TODO Auto-generated method stub
		List<Role> list = null;
		try {
			list = roleDao.findAutho();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return list;
	}

}
