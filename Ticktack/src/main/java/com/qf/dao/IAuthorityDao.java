package com.qf.dao;

import java.util.List;
import java.util.Map;

import com.qf.entity.Authority;

public interface IAuthorityDao {

	public List<String> findRoleByNo(String no);

	public List<String> findTitleByNo(String no);

	public List<Authority> findParentId();

	public int count();

	public List<Authority> findAllAuthority(Map<String, Object> info);

	public void deleteById(int id);

	public Authority findById(int id);

	public void add(Authority aut);

	public List<Authority> findByPid(Integer parentId);

}
