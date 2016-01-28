package me.figochan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import me.figochan.entities.Group;
import me.figochan.entities.User;
import me.figochan.service.GroupService;
import me.figochan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> addGroup(HttpSession session,String groupname){
		Map<String,String> hm = new HashMap<String,String>();
		String username=(String)session.getAttribute("username");
		User user=this.testLogin(username);
		if(user==null){
			hm.put("result", "还没有登录");
			return hm;
		}
		if(groupname==null || groupname.equals("")){
			hm.put("result", "分组名称不能为空");
			return hm;
		}
		Group temp=this.groupService.getGroupByUserAndName(user, groupname);
		if(temp!=null){
			hm.put("result", "该分组已经存在");
			return hm;
		}
		Group group=new Group(groupname, user);
		String result=this.groupService.addGroup(group);
		hm.put("result", result);
		return hm;
	}
	
	public User testLogin(String username){
		if(username==null || username.equals("")){
			return null;
		}
		User user=this.userService.getUserByUsername(username);
		if(user==null){
			return null;
		}
		return user;
	}
}
