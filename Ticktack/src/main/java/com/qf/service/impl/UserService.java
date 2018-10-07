package com.qf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.dao.IUserDao;
import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.utils.StringUtil;
import com.qf.vo.PageBean;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public User userLogin(String no, String password) {
		// TODO Auto-generated method stub

		if (StringUtil.isEmpty(no)) {
			throw new RuntimeException("账号不能为空");
		}
		
		if (StringUtil.isEmpty(password)) {
			throw new RuntimeException("密码不能为空");
		}

		User user = null;
		
		try {
			user = userDao.findByNo(no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		if (user == null) {
			throw new RuntimeException("账号错误");
		}
		
		if (!user.getPassword().equals(password)) {
			throw new RuntimeException("密码错误");
		}

		return user;
	}

	@Override
	public User findUserByNo(String no) {
		// TODO Auto-generated method stub
		User u = null;
		try {
			u = userDao.findByNo(no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public PageBean<User> findUsersByPage(Integer page, Integer limit, String no, Integer flag) {
		// TODO Auto-generated method stub
		PageBean<User> pageInfo = new PageBean<>();

		pageInfo.setCurrentPage(page);

		// 设置每页显示的记录数
		pageInfo.setPageSize(limit);

		// 获取表中的记录总数
		int count = userDao.count();
		// 设置总记录数
		pageInfo.setCount(count);
		// 计算总页数
		if (count % pageInfo.getPageSize() == 0) {
			pageInfo.setTotalPage(count / pageInfo.getPageSize());
		} else {
			pageInfo.setTotalPage(count / pageInfo.getPageSize() + 1);
		}
		// 根据页码计算分页查询时的开始索引
		int index = (page - 1) * pageInfo.getPageSize();
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		map.put("no", no);
		map.put("flag", flag);
		List<User> list = userDao.findAll(map);
		pageInfo.setPageInfos(list);

		return pageInfo;
	}

	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		
		User u = null;
		
		try {
			u = userDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return u;
	}

	@Override
	public void deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		
		try {
			User user = userDao.findById(id);
			if(user != null) {
				userDao.deleteById(id);
			} else {
				throw new RuntimeException("用户是不可能存在的");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
		if(user == null) {
			throw new RuntimeException("编辑是不可能编辑的");
		}
		
		User u = userDao.findById(user.getId());
		try {
			if (u != null) {
				userDao.update(user);
			} else {
				throw new RuntimeException("用户是不可能找到的");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}

}
