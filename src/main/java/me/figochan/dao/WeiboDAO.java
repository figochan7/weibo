package me.figochan.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import me.figochan.entities.Group;
import me.figochan.entities.User;
import me.figochan.entities.Weibo;

public interface WeiboDAO {
	public boolean addWeibo(Weibo weibo);
	public boolean updateCommentCount(Weibo weibo);
	public boolean updatePraiseCount(Weibo weibo);
	public boolean updateForwardCount(Weibo weibo);
	public Weibo getWeiboById(String id);
	
	public List<Weibo> getHotWeibo();
	public List<Weibo> getWeibosByFilter(User user, Group group,int firstIndex, int count);
	public long getWeibosCountByFilter(User user, Group group);
	public List<Weibo> getWeiboByUser(User user,int firstIndex, int count);
	public long getWeiboCountByUser(User user);
}
