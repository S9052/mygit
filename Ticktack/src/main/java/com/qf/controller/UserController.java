package com.qf.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.utils.JsonBeanUtil;
import com.qf.vo.JsonBean;
import com.qf.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/userLogin")
	@ResponseBody
	public JsonBean login(String no, String password, HttpSession session){
		
		UsernamePasswordToken token = new UsernamePasswordToken(no, password);
		session.setAttribute("no", no);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return JsonBeanUtil.createJsonBean(1, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
		
	}
	
	@RequestMapping("/userLogout")
	@ResponseBody
	public JsonBean logout(HttpSession session) {
		
		Subject subject = SecurityUtils.getSubject();
        //退出
        subject.logout();
		
//		session.invalidate();
		return JsonBeanUtil.createJsonBean(1, null);
		
	}
	
	@RequestMapping("/findUserByNo")
	@ResponseBody
	public JsonBean findUserByNo(HttpSession session) {
		
		String no = (String) session.getAttribute("no");
		
		try {
			User u = userService.findUserByNo(no);
			return JsonBeanUtil.createJsonBean(1, u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
		
	}
	
	@RequestMapping("/findAllUsers/page")
	@ResponseBody
	public Map<String, Object> findAllUsers(Integer page, Integer limit, String no, Integer flag){
		
		if (no != null && no.equals("")) {
			no = null;
		}
		
		Map<String, Object> map = new HashMap<>();
		PageBean<User> pageInfo = userService.findUsersByPage(page, limit, no, flag);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@RequestMapping("/deleteUserById/{id}")
	@ResponseBody
	public JsonBean deleteUserById(@PathVariable("id")Integer id) {
		
		try {
			userService.deleteUserById(id);
			return JsonBeanUtil.createJsonBean(1, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
		
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public JsonBean updateUser(User user) {
		
		try {
			userService.updateUser(user);
			return JsonBeanUtil.createJsonBean(1, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
		
	}
	
	@RequestMapping("/findUserById")
	@ResponseBody
	public JsonBean findUserById(Integer id) {
		try {
			User u = userService.findUserById(id);
			return JsonBeanUtil.createJsonBean(1, u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonBeanUtil.createJsonBean(0, e.getMessage());
		}
	}
	
}
