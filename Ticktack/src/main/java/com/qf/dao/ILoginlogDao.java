package com.qf.dao;

import java.util.List;
import java.util.Map;

import com.qf.entity.Loginlog;

public interface ILoginlogDao {
    
	public int count();
	
	public List<Loginlog> findAll(Map<String, Object> info);
}