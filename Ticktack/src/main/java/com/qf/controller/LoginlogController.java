package com.qf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qf.entity.Loginlog;
import com.qf.service.ILoginlogService;
import com.qf.vo.PageBean;


@Controller
@RequestMapping("/Ticktack_Office")
public class LoginlogController {

	@Autowired
	private ILoginlogService loginlogService;
	
	@RequestMapping("/findAllLogs/page")
	@ResponseBody
	public Map<String, Object> findAll(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<Loginlog> pageInfo = loginlogService.findAllLogs(page, limit);
		
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
}
