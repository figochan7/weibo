package me.figochan.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import me.figochan.entities.Follow;
import me.figochan.entities.Group;
import me.figochan.entities.Message;
import me.figochan.entities.User;
import me.figochan.service.FollowService;
import me.figochan.service.GroupService;
import me.figochan.service.MessageService;
import me.figochan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/follow")
public class FollowController {
	@Autowired
	private FollowService followService;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/getFollowed",method=RequestMethod.GET)
	public String getFollowedByFollow(HttpSession session,String groupname,Map<String,Object> model){
		String username = (String)session.getAttribute("username");

		User user=testLogin(username);
		if(user==null){
			return "login";
		}
		List<Follow> followList=new ArrayList<>();
		if(groupname!=null){
			try {
				groupname=new String(groupname.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String groupid=this.groupService.getGroupByName(groupname).getId();
			followList=this.followService.getFollowedByFollowAndGroup(user.getId(), groupid);
		}else{
			followList=this.followService.getFollowedByFollow(user.getId());
		}
		List<Group> groupList=this.groupService.getGroupByUser(user.getId());
		int unReadCount=this.messageService.getUnReadMessageCountByToId(user.getId());
		model.put("user", user);
		model.put("followList", followList);
		model.put("groupList", groupList);
		model.put("unReadCount",unReadCount );
		model.put("followCount", followList.size());
		return "follow";
	}
	
	@RequestMapping(value="/getFans",method=RequestMethod.GET)
	public String getFollowByFollowed(HttpSession session,Map<String,Object> model){
		String username = (String)session.getAttribute("username");

		User user=testLogin(username);
		if(user==null){
			return "login";
		}
		List<Follow> followList=this.followService.getFansByFollowed(user.getId());
		
		
		int unReadCount=this.messageService.getUnReadMessageCountByToId(user.getId());
		model.put("user", user);
		model.put("followList", followList);
		model.put("unReadCount",unReadCount );
		model.put("followedCount", followList.size());
		return "fans";
	}
	
	@RequestMapping(value="/cancel",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> cancelFollow(String id){
		Map<String,String> hm = new HashMap<String,String>();
		Follow follow=this.followService.getFollowById(id);
		if(follow==null){
			hm.put("result", "没有关注该用户");
			return hm;
		}
		String r1=this.userService.minusFansCount(follow.getFollowed());
		String r2=this.userService.minusFollowCount(follow.getFollow());
		if(!r1.equals("success") || !r2.equals("success")){
			hm.put("result", "取消关注失败");
			return hm;
		}
		String result=this.followService.deleteFollow(follow);
		hm.put("result", result);
		return hm;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> addFollow(HttpSession session,String id){
		Map<String,String> hm = new HashMap<String,String>();
		String username = (String)session.getAttribute("username");
		User user=testLogin(username);
		if(user==null){
			hm.put("result", "请重新登录");
			return hm;
		}
		User followUser=this.userService.getUserById(id);
		if(followUser==null){
			hm.put("result", "该用户不存在");
			return hm;
		}
		String r1=this.userService.addFansCount(followUser);
		String r2=this.userService.addFollowCount(user);
		Message message=new Message(user, followUser, new Date(), "用户@"+user.getUsername()+"关注了你", 0);
		String r3=this.messageService.addMessage(message);
		if(!r1.equals("success") || !r2.equals("success") || !r3.equals("success")){
			hm.put("result", "关注失败");
			return hm;
		}
		Follow follow=new Follow(user, followUser, null);
		String result=this.followService.addFollow(follow);
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
