package me.figochan.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.figochan.entities.Comment;
import me.figochan.entities.Message;
import me.figochan.entities.Picture;
import me.figochan.entities.User;
import me.figochan.entities.Weibo;
import me.figochan.service.MessageService;
import me.figochan.service.PictureService;
import me.figochan.service.UserService;
import me.figochan.service.WeiboService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/weibo")
public class WeiboController {
	@Autowired
	private WeiboService weiboService;
	@Autowired
	private UserService userService;
	@Autowired
	private PictureService pictureService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/addPics",method=RequestMethod.POST)
	public void publicWeiboWithPics(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file,String text,
			String username){
		User user=this.testLogin(username);
		if(user==null){
			try {
				response.getWriter().write("还没有登录");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
		response.setCharacterEncoding("utf-8");
		try {
			text=new String(text.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			try {
				response.getWriter().write("文件编码失败");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
		// 文件保存路径  ctxPath本地路径
		String ctxPath=request.getRealPath("/resources/upload")+File.separator;
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
		String ymd = sdf.format(new Date());  

		ctxPath += File.separator + ymd + File.separator;  */

		System.out.println("ctxpath="+ctxPath);
		File tempfile = new File(ctxPath);    

		if (!tempfile.exists()) {  
			System.out.println(tempfile.exists());
			tempfile.mkdirs();  
		}
		Weibo weibo=new Weibo(user, text, new Date(), 0, 0, 0, null);
		String saveresult=this.weiboService.addWeibo(weibo);
		if(!saveresult.equals("success")){
			try {
				response.getWriter().write("发布微博失败");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		String fileName = null; 
		
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
			MultipartFile mf = entity.getValue();  
			try {
				fileName = new String(mf.getOriginalFilename().getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e1) {
				try {
					response.getWriter().write("文件编码失败");
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}//获取原文件名  
			String uuidName = UUID.randomUUID().toString() ;
			int tempIndex = fileName.lastIndexOf(".");
		    String suffix = fileName.substring(tempIndex);
		    System.out.println(suffix);
			File uploadFile = new File(ctxPath + uuidName+suffix);
			try { 
				FileCopyUtils.copy(mf.getBytes(), uploadFile);  
				Picture  picture=new Picture(fileName, uuidName+suffix, ctxPath, weibo);
				String result=this.pictureService.addPicture(picture);
				
			} catch (IOException e) {  
				try {
					response.getWriter().write("上传失败");
					return;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			

		}  		
		String countResult=this.userService.addWeiboCount(user);
		if(!countResult.equals("success")){
			try {
				response.getWriter().write("发布微博失败");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		try {
			response.getWriter().write("success");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> publicWeibo(HttpSession session,String text){
		Map<String,String> hm = new HashMap<String,String>();
		String username=(String)session.getAttribute("username");
		User user=this.testLogin(username);
		if(user==null){
			hm.put("result", "还没有登录");
			return hm;
		}
		Weibo weibo=new Weibo(user, text, new Date(), 0, 0, 0, null);
		String result=this.weiboService.addWeibo(weibo);
		String result1=this.userService.addWeiboCount(user);
		if(result.equals("success") && result1.equals("success")){
			hm.put("result", "success");
			return hm;
		}
		hm.put("result", result);
		return hm;
	}
	
	@RequestMapping(value="/forward",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> forwardWeibo(HttpSession session,String text,String weiboid){
		Map<String,String> hm = new HashMap<String,String>();
		System.out.println(text);
		System.out.println(weiboid);
		String username=(String)session.getAttribute("username");
		User user=this.testLogin(username);
		if(user==null){
			hm.put("result", "还没有登录");
			return hm;
		}
		if(text==null || text.equals("")){
			hm.put("result", "微博内容不能为空");
			return hm;
		}
		Weibo sourceWeibo=this.weiboService.getWeiboById(weiboid);
		if(sourceWeibo==null){
			hm.put("result", "原微博不存在");
			return hm;
		}
		Weibo weibo=new Weibo(user, text, new Date(), 0, 0, 0, sourceWeibo);
		String fresult=this.weiboService.updateForwardCount(sourceWeibo);
		String result=this.weiboService.addWeibo(weibo);
		String result1=this.userService.addWeiboCount(user);
		Message message=new Message(user, sourceWeibo.getUser(), new Date(), "用户@"+user.getUsername()+" 转发了您的微博", 0);
		String messageResult=this.messageService.addMessage(message);
		if(messageResult.equals("success") || result.equals("success") && result1.equals("success") && fresult.equals("success")){
			hm.put("result", "success");
			return hm;
		}
		hm.put("result", result);
		return hm;
	}
	
	@RequestMapping(value="/praise",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> praiseWeibo(HttpSession session,String weiboid){
		Map<String,String> hm = new HashMap<String,String>();
		String username=(String)session.getAttribute("username");
		User user=this.testLogin(username);
		if(user==null){
			hm.put("result", "还没有登录");
			return hm;
		}
		Weibo sourceWeibo=this.weiboService.getWeiboById(weiboid);
		if(sourceWeibo==null){
			hm.put("result", "微博不存在");
			return hm;
		}
		Message message=new Message(user, sourceWeibo.getUser(), new Date(), "用户@"+user.getUsername()+"赞了你的微博", 0);
		String mr=this.messageService.addMessage(message);
		String result=this.weiboService.updatePraiseCount(sourceWeibo);
		hm.put("result", result);
		return hm;
	}
	@RequestMapping(value="/id/{id}",method=RequestMethod.GET)
	public @ResponseBody Weibo getWeiboById(@PathVariable String id){
		Weibo weibo = this.weiboService.getWeiboById(id);
		return weibo;
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
