package com.qf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qf.entity.Authority;
import com.qf.service.IAuthorityService;
import com.qf.utils.JsonBeanUtil;
import com.qf.vo.JsonBean;
import com.qf.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class AuthorityController {

	@Autowired
	private IAuthorityService authoService;
	
	@RequestMapping("/findParentId")
	@ResponseBody
	public JsonBean findParentId() {
		Map<String, Object> list = null;
		
		try {
			list = authoService.findParentId();
			return JsonBeanUtil.createJsonBean(1, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/findAllAutho/page")
	@ResponseBody
	public Map<String, Object> findAllAutho(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<Authority> pageInfo = authoService.findAllAutho(page, limit);
		
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@RequestMapping("/deleteAuthoById/{id}")
	@ResponseBody
	public JsonBean deleteAuthoById(@PathVariable("id") int id) {
		try {
			authoService.deleteAuthoById(id);
			return JsonBeanUtil.createJsonBean(1, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/addAutho")
	@ResponseBody
	public JsonBean addAutho(Authority aut) {
		try {
			authoService.addAutho(aut);
			return JsonBeanUtil.createJsonBean(1, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
	}
	
	@RequestMapping("/findAuthoByPid")
	@ResponseBody
	public List<Authority> findAuthoByPid(Integer parentId) {
		List<Authority> autho = null;
		try {
			
			autho = authoService.findAuthoByPid(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return autho;
	}
	
	
}
