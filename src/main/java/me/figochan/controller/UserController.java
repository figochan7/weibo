package me.figochan.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import me.figochan.entities.Follow;
import me.figochan.entities.Group;
import me.figochan.entities.User;
import me.figochan.entities.Weibo;
import me.figochan.service.FollowService;
import me.figochan.service.GroupService;
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
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private WeiboService weiboService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private FollowService followService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> addUser(String username, String password, String birthday,String telephone, String email){
		Map<String,String> hm = new HashMap<String,String>();
		if(!this.checkFields(username, password, birthday, telephone, email)){
			hm.put("result", "输入的字段不合法");
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
		User user=new User(username, password, telephone, email, _birthday, "","default.png",0,0,0,0);
		String result=this.userService.saveUser(user);
		hm.put("result", result);
		return hm;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> userLogin(HttpSession session,String username,String password){
		Map<String,String> hm = new HashMap<String,String>();
		User user=this.userService.getUserByUsername(username);
		if(user==null){
			hm.put("result", "该用户不存在");
			return hm;
		}
		if(!user.getPassword().equals(password)){
			hm.put("result", "密码不正确");
			return hm;
		}
		//添加session
		session.setAttribute("username", username);
		System.out.println((String)session.getAttribute("username"));
		hm.put("result", "success");
		return hm;		
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String userIndex(HttpSession session,String groupname,Integer pageIndex,
			Integer itemsPerPage,Map<String,Object> model){
		String username = (String)session.getAttribute("username");
		if(groupname!=null){
			try {
				groupname=new String(groupname.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(username==null || username.equals("")){
			return "login";
		}
		User user=this.userService.getUserByUsername(username);
		if(pageIndex==null){
			pageIndex = 1;
		}
		if(itemsPerPage==null){
			itemsPerPage=10;
		}
		int firstIndex = (pageIndex-1)*itemsPerPage;
		int count = itemsPerPage;
		long totalCount = this.weiboService.getWeibosCountByUserAndGroup(user.getId(), groupname);
		long totalPages = totalCount/itemsPerPage;
		if(totalCount%itemsPerPage!=0){
			totalPages += 1;
		}
		if(totalPages==0){
			totalPages=1;
		}
		List<Weibo> weiboList=this.weiboService.getWeibosByUserAndGroup(user.getId(), groupname, firstIndex, count);
		List<Group> groupList=this.groupService.getGroupByUser(user.getId());
		int fansCount=this.followService.getFansCountByFollowed(user.getId());
		int followCount=this.followService.getFollowedCountByFollow(user.getId());
		int unReadCount=this.messageService.getUnReadMessageCountByToId(user.getId());
		List<Weibo> hotWeiboList=this.weiboService.getHotWeibo();
		List<User> hotUserList=this.userService.getHotUser();

		model.put("weiboList", weiboList);
		model.put("user", user);
		model.put("groupList",groupList );
		model.put("fansCount", fansCount);
		model.put("followCount", followCount);
		model.put("unReadCount",unReadCount );
		model.put("hotWeiboList",hotWeiboList );
		model.put("hotUserList",hotUserList );
		model.put("totalPages", totalPages);
		model.put("pageIndex", pageIndex);
		return "index";
	}
	
	@RequestMapping(value="/myindex",method=RequestMethod.GET)
	public String myIndex(HttpSession session,Integer pageIndex,
			Integer itemsPerPage,Map<String,Object> model){
		String username = (String)session.getAttribute("username");
		if(username==null || username.equals("")){
			return "login";
		}
		User user=this.userService.getUserByUsername(username);
		if(pageIndex==null){
			pageIndex = 1;
		}
		if(itemsPerPage==null){
			itemsPerPage=10;
		}
		int firstIndex = (pageIndex-1)*itemsPerPage;
		int count = itemsPerPage;
		long totalCount = this.weiboService.getWeiboCountByUser(user);
		long totalPages = totalCount/itemsPerPage;
		if(totalCount%itemsPerPage!=0){
			totalPages += 1;
		}
		if(totalPages==0){
			totalPages=1;
		}
		List<Weibo> weiboList=this.weiboService.getWeiboByUser(user, firstIndex, count);

		List<Group> groupList=this.groupService.getGroupByUser(user.getId());
		int unReadCount=this.messageService.getUnReadMessageCountByToId(user.getId());


		model.put("weiboList", weiboList);
		model.put("user", user);
		model.put("groupList",groupList );
		model.put("unReadCount",unReadCount );
		model.put("totalPages", totalPages);
		model.put("pageIndex", pageIndex);
		return "myindex";
	}
	
	@RequestMapping(value="/oindex",method=RequestMethod.GET)
	public String otherUserIndex(HttpSession session,String id,Integer pageIndex,	Integer itemsPerPage,Map<String,Object> model){
		if(pageIndex==null){
			pageIndex = 1;
		}
		if(itemsPerPage==null){
			itemsPerPage=10;
		}
		String username = (String)session.getAttribute("username");
		User user=this.userService.getUserByUsername(username);
		User user1=this.userService.getUserById(id);
		if(user==null || user1==null){
			return "login";
		}
		int firstIndex = (pageIndex-1)*itemsPerPage;
		int count = itemsPerPage;
		long totalCount = this.weiboService.getWeiboCountByUser(user1);
		long totalPages = totalCount/itemsPerPage;
		if(totalCount%itemsPerPage!=0){
			totalPages += 1;
		}
		if(totalPages==0){
			totalPages=1;
		}
		List<Weibo> weiboList=this.weiboService.getWeiboByUser(user1, firstIndex, count);
		Follow follow=this.followService.getFollowByFollowAndFollowed(user, user1);
		int isFollow=0;
		if(follow!=null){
			isFollow=1;
		}

		model.put("weiboList", weiboList);
		model.put("user1", user1);
		model.put("user", user);
		model.put("isFollow", isFollow);
		model.put("totalPages", totalPages);
		model.put("pageIndex", pageIndex);
		return "oindex";
	}
	
	@RequestMapping(value="check",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> checkUser(String username){
		Map<String,Object> hm = new HashMap<String,Object>();
		User user=this.userService.getUserByUsername(username);
		if(user!=null){
			hm.put("valid", false);
			return hm;
		}
		hm.put("valid", true);
		return hm;		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logOut(HttpSession session){
		session.removeAttribute("username");
		return "login";
	}
	
	
	
	public  boolean checkFields(String username, String password, String birthday,String telephone, String email){
		if(username==null || password==null || birthday==null || email==null || telephone==null){
			return false;
		}		
		if(username.equals("") || password.equals("") || birthday.equals("") || email.equals("") || telephone.equals("")){
			return false;
		}		
		return true;		
	}
}
