package com.qf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.dao.IAuthorityDao;
import com.qf.entity.Authority;
import com.qf.service.IAuthorityService;
import com.qf.vo.PageBean;

@Service
public class AuthorityService implements IAuthorityService {

	@Autowired
	private IAuthorityDao authoDao;

	@Override
	public Map<String, Object> findParentId() {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			// 查询所有菜单
			List<Authority> allMenu = authoDao.findParentId();
			// 根节点
			List<Authority> rootMenu = new ArrayList<Authority>();
			for (Authority nav : allMenu) {
				if (nav.getParentId().equals(0)) {
					// 父节点是0的，为根节点。
					rootMenu.add(nav);
				}
			}

			// 为根菜单设置子菜单，getClild是递归调用的
			for (Authority nav : rootMenu) {
				/* 获取根节点下的所有子节点 使用getChild方法 */
				List<Authority> childList = getChild(nav.getId(), allMenu);
				nav.setChildren(childList);// 给根节点设置子节点
			}
			/**
			 * 输出构建好的菜单数据
			 * 
			 */
			data.put("list", rootMenu);
			return data;
		} catch (Exception e) {
			// data.put("success", "false");
			// data.put("list", new ArrayList());
			return null;
		}
	}

	private List<Authority> getChild(Integer id, List<Authority> allMenu) {
		// TODO Auto-generated method stub
		// 子菜单
		List<Authority> childList = new ArrayList<Authority>();
		for (Authority nav : allMenu) {
			// 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
			// 相等说明：为该根节点的子节点。
			if (nav.getParentId().equals(id)) {
				childList.add(nav);
			}
		}
		// 递归
		for (Authority nav : childList) {
			nav.setChildren(getChild(nav.getId(), allMenu));
		}
		// 如果节点下没有子节点，返回一个空List（递归退出）
		if (childList.size() == 0) {
			return new ArrayList<Authority>();
		}
		return childList;
	}

	@Override
	public PageBean<Authority> findAllAutho(int page, int limit) {
		// TODO Auto-generated method stub
		PageBean<Authority> pageInfo = new PageBean<>();

		pageInfo.setCurrentPage(page);

		pageInfo.setPageSize(limit);

		int count = authoDao.count();
		pageInfo.setCount(count);

		if (count % pageInfo.getPageSize() == 0) {
			pageInfo.setTotalPage(count / pageInfo.getPageSize());
		} else {
			pageInfo.setTotalPage(count / pageInfo.getPageSize() + 1);
		}
		int index = (page - 1) * pageInfo.getPageSize();
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Authority> list = authoDao.findAllAuthority(map);
		pageInfo.setPageInfos(list);

		return pageInfo;
	}
	
	@Override
	public Authority findAuthoById(int id) {
		// TODO Auto-generated method stub
		return authoDao.findById(id);
	}

	@Override
	public void deleteAuthoById(int id) {
		// TODO Auto-generated method stub
		Authority autho = authoDao.findById(id);
		try {
			if(autho != null) {
				authoDao.deleteById(id);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void addAutho(Authority autho) {
		// TODO Auto-generated method stub
		if(autho == null) {
			throw new RuntimeException("信息不能为空！");
		}
		try {
			authoDao.add(autho);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Authority> findAuthoByPid(Integer parentId) {
		// TODO Auto-generated method stub
		return authoDao.findByPid(parentId);
	}

}
