package me.figochan.controller;

import java.io.UnsupportedEncodingException;
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
import me.figochan.service.MessageService;
import me.figochan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public String getFollowedByFollow(HttpSession session,Map<String,Object> model){
		String username = (String)session.getAttribute("username");
		User user=testLogin(username);
		if(user==null){
			return "login";
		}
		List<Message> messageList=this.messageService.getMessagesByToId(user.getId());
		
		int unReadCount=this.messageService.getUnReadMessageCountByToId(user.getId());
		model.put("user", user);
		model.put("messageList",messageList );
		model.put("unReadCount",unReadCount );
		model.put("messageCount", messageList.size());
		return "message";
	}
	
	@RequestMapping(value="/mark",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> markRead(HttpSession session,String id){
		Map<String,String> hm = new HashMap<String,String>();
		String username = (String)session.getAttribute("username");
		User user=testLogin(username);
		if(user==null){
			hm.put("result", "请重新登录");
			return hm;
		}
		Message message=this.messageService.getMessageById(id);
		if(message==null){
			hm.put("result", "该消息不存在");
			return hm;
		}
		String result=this.messageService.updateIsRead(id);
		hm.put("result", result);
		return hm;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> deleteMessage(HttpSession session,String id){
		Map<String,String> hm = new HashMap<String,String>();
		String username = (String)session.getAttribute("username");
		User user=testLogin(username);
		if(user==null){
			hm.put("result", "请重新登录");
			return hm;
		}
		Message message=this.messageService.getMessageById(id);
		if(message==null){
			hm.put("result", "该消息不存在");
			return hm;
		}
		String result=this.messageService.deleteMessage(message);
		String r1=this.userService.minusMessageCount(user);
		if(!r1.equals("success") || !result.equals("success")){
			hm.put("result", "删除消息失败");
		}else{
			hm.put("result", "success");
		}
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
