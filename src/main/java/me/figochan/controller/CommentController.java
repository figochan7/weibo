package me.figochan.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import me.figochan.entities.Comment;
import me.figochan.entities.Group;
import me.figochan.entities.Message;
import me.figochan.entities.User;
import me.figochan.entities.Weibo;
import me.figochan.service.CommentService;
import me.figochan.service.MessageService;
import me.figochan.service.UserService;
import me.figochan.service.WeiboService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private WeiboService weiboService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/id/{id}",method=RequestMethod.GET)
	public @ResponseBody List<Comment> getCommentByWeibo(@PathVariable String id){
		List<Comment> list = this.commentService.getCommentsByWeibo(id);
		if(list!=null && list.size()>10){
			list=list.subList(0, 10);
		}
		if(list!=null && list.size()>0){
			for (int i = 0; i <list.size(); i++) {
				list.get(i).setWeibo(null);
			}
		}
		return list;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> addComment(HttpSession session,String id,String forward,String comment){
		Map<String,String> hm = new HashMap<String,String>();
		String username=(String)session.getAttribute("username");
		User user=this.testLogin(username);
		if(user==null){
			hm.put("result", "还没有登录");
			return hm;
		}
		if(comment==null || comment.equals("")){
			hm.put("result", "评论内容不能为空");
			return hm;
		}
		Weibo weibo=this.weiboService.getWeiboById(id);
		if(weibo==null){
			hm.put("result", "原微博不存在");
			return hm;
		}
		Comment _comment=new Comment(user, comment, new Date(), weibo, 0);
		String result=this.commentService.addComment(_comment);
		String weiboresult=this.weiboService.updateCommentCount(weibo);
		User sourceUser=weibo.getUser();
		String message="用户@"+user.getUsername()+" 评论了您的微博";
		if(!result.equals("success") || !weiboresult.equals("success")){
			hm.put("result", "评论失败，稍后再试");
			return hm;
		}
		if(forward.equals("yes")){
			Weibo newweibo=new Weibo(user, comment, new Date(), 0, 0, 0, weibo);
			String weiboCount=this.userService.addWeiboCount(user);
			String forwardResult=this.weiboService.addWeibo(newweibo);
			String forwardCount=this.weiboService.updateForwardCount(weibo);
			message="用户@"+user.getUsername()+" 评论并且转发了您的微博";
			if(!weiboCount.equals("success") || !forwardResult.equals("success") || !forwardCount.equals("success")){
				hm.put("result", "评论失败，稍后再试");
				return hm;
			}
		}
		Message _message=new Message(user, sourceUser, new Date(), message, 0);
		String messageResult=this.messageService.addMessage(_message);
		if(!messageResult.equals("success")){
			hm.put("result", messageResult);
			return hm;
		}
		hm.put("result", "success");
		return hm;
	}
	
	@RequestMapping(value="/praise",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> praise(HttpSession session,String id){
		Map<String,String> hm = new HashMap<String,String>();
		String username=(String)session.getAttribute("username");
		User user=this.testLogin(username);
		if(user==null){
			hm.put("result", "还没有登录");
			return hm;
		}
		Comment comment=this.commentService.getCommentById(id);
		if(comment==null){
			hm.put("result", "该评论不存在");
			return hm;
		}
		String result=this.commentService.updatePraiseCount(id, comment.getPraiseCount()+1);
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
