package me.figochan.service;

import java.util.List;

import me.figochan.entities.User;
import me.figochan.entities.Weibo;

public interface WeiboService {
	public String addWeibo(Weibo weibo);
	public String updateCommentCount(Weibo weibo);
	public String updatePraiseCount(Weibo weibo);
	public String updateForwardCount(Weibo weibo);
	public Weibo getWeiboById(String id);
	
	public List<Weibo> getHotWeibo();
	public List<Weibo> getWeibosByUserAndGroup(String userid,String groupname,int firstIndex,int count);
	public long getWeibosCountByUserAndGroup(String userid,String groupname);
	public List<Weibo> getWeiboByUser(User user,int firstIndex, int count);
	public long getWeiboCountByUser(User user);
}
