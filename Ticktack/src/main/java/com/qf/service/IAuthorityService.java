package com.qf.service;

import java.util.List;
import java.util.Map;

import com.qf.entity.Authority;
import com.qf.vo.PageBean;

public interface IAuthorityService {
	
	public Map<String,Object> findParentId();
	
	public PageBean<Authority> findAllAutho(int page, int limit);
	
	public Authority findAuthoById(int id);
	
	public void deleteAuthoById(int id);
	
	public void addAutho(Authority autho);
	
	public List<Authority> findAuthoByPid(Integer parentId);
}
