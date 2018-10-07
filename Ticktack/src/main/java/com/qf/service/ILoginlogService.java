package com.qf.service;


import com.qf.entity.Loginlog;
import com.qf.vo.PageBean;


public interface ILoginlogService {
	
	public PageBean<Loginlog> findAllLogs(int page, int limit);
}
