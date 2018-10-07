package com.qf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.dao.ILoginlogDao;
import com.qf.entity.Loginlog;
import com.qf.service.ILoginlogService;
import com.qf.vo.PageBean;

@Service
public class LoginlogService implements ILoginlogService{

	@Autowired
	private ILoginlogDao loginlogDao;

	@Override
	public PageBean<Loginlog> findAllLogs(int page, int limit) {
		// TODO Auto-generated method stub
		PageBean<Loginlog> pageInfo = new PageBean<>();
		
		pageInfo.setCurrentPage(page);
		
		// 设置每页显示的记录数
		pageInfo.setPageSize(limit);
		
		// 获取表中的记录总数
		int count = loginlogDao.count();
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
		List<Loginlog> list = loginlogDao.findAll(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	
	}
}
