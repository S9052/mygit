package com.qf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qf.entity.Role;
import com.qf.service.IRoleService;
import com.qf.utils.JsonBeanUtil;
import com.qf.vo.JsonBean;
import com.qf.vo.PageBean;

@RequestMapping("/Ticktack_Office")
@Controller
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/findAllRole/page")
	@ResponseBody
	public Map<String, Object> findAllRole(Integer page, Integer limit, String name){
		
		Map<String, Object> map = new HashMap<>();
		PageBean<Role> pageInfo = roleService.findAllRole(page, limit, name);

		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());

		return map;
	}
	
	@RequestMapping("/deleteRoleById/{id}")
	@ResponseBody
	public JsonBean deleteRole(@PathVariable("id") Integer id) {
		try {
			roleService.deleteRoleById(id);
			return JsonBeanUtil.createJsonBean(1, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/findAutho")
	@ResponseBody
	public JsonBean findTitleById(Integer id) {
		try {
			List<Role> list = roleService.findAuthoById(id);
			return JsonBeanUtil.createJsonBean(1, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
	}
	
}
