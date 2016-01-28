package me.figochan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import me.figochan.entities.User;
import me.figochan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/setting")
public class SettingController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/info",method=RequestMethod.GET)
	public String userIndex(HttpSession session,Map<String,Object> model){
		String username = (String)session.getAttribute("username");
		if(username==null || username.equals("")){
			return "login";
		}
		User user=this.userService.getUserByUsername(username);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String birthday=sdf.format(user.getBirthday());
		model.put("birthday", birthday);
		model.put("user", user);
		return "setting";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> updateUser(HttpSession session,String birthday,String telephone,String email,String introduction){
		Map<String,String> hm = new HashMap<String,String>();
		String username = (String)session.getAttribute("username");
		if(username==null || username.equals("")){
			hm.put("result", "请重新登录");
			return hm;
		}
		User user=this.userService.getUserByUsername(username);
		
		if(user==null){
			hm.put("result", "该用户不存在");
			return hm;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date _birthday=null;
		try {
			_birthday=sdf.parse(birthday);
		} catch (ParseException e) {
			hm.put("result", "日期格式不正确");
			return hm;
		}
		System.out.println(introduction);
		User temp=new User(username, user.getPassword(), telephone, email, _birthday, introduction, user.getAvatar(), user.getMessageCount(), user.getFollowCount(), user.getFansCount(), user.getWeiboCount());
		temp.setId(user.getId());
		String result=this.userService.updateUser(temp);
		hm.put("result", result);
		return hm;		
	}
	
	@RequestMapping(value="/pwd",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> updatePassword(HttpSession session,String password,String newpassword){
		Map<String,String> hm = new HashMap<String,String>();
		String username = (String)session.getAttribute("username");
		if(username==null || username.equals("")){
			hm.put("result", "请重新登录");
			return hm;
		}
		User user=this.userService.getUserByUsername(username);
		
		if(user==null){
			hm.put("result", "该用户不存在");
			return hm;
		}
		if(!user.getPassword().equals(password)){
			hm.put("result", "旧密码不正确");
			return hm;
		}
		
		String result=this.userService.updatePassword(user, newpassword);
		hm.put("result", result);
		return hm;		
	}
	
	@RequestMapping(value="/avatar",method=RequestMethod.GET)
	public String userAvatar(HttpSession session,Map<String,Object> model){
		String username = (String)session.getAttribute("username");
		if(username==null || username.equals("")){
			return "login";
		}
		User user=this.userService.getUserByUsername(username);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String birthday=sdf.format(user.getBirthday());
		model.put("birthday", birthday);
		model.put("user", user);
		return "avatar";
	}
}
