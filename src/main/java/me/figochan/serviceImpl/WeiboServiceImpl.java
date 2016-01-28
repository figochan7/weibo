package me.figochan.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.figochan.dao.GroupDAO;
import me.figochan.dao.UserDAO;
import me.figochan.dao.WeiboDAO;
import me.figochan.entities.Group;
import me.figochan.entities.User;
import me.figochan.entities.Weibo;
import me.figochan.service.WeiboService;

@Component
public class WeiboServiceImpl implements WeiboService {

	@Autowired
	private WeiboDAO weiboDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private GroupDAO groupDAO;
	
	@Transactional
	@Override
	public String addWeibo(Weibo weibo) {
		boolean result = weiboDAO.addWeibo(weibo);
		if(result){
			return "success";
		}
		return "添加失败！请稍后再试！";
	}

	@Transactional
	@Override
	public String updateCommentCount(Weibo weibo) {
		boolean result=this.weiboDAO.updateCommentCount(weibo);
		if(result){
			return "success";
		}
		return "评论失败";
	}

	@Transactional
	@Override
	public String updatePraiseCount(Weibo weibo) {
		boolean result=this.weiboDAO.updatePraiseCount(weibo);
		if(result){
			return "success";
		}
		return "点赞失败";
	}

	@Transactional
	@Override
	public String updateForwardCount(Weibo weibo) {
		boolean result=this.weiboDAO.updateForwardCount(weibo);
		if(result){
			return "success";
		}
		return "转发失败";
	}

	@Override
	public List<Weibo> getHotWeibo() {
		return this.weiboDAO.getHotWeibo();
	}

	@Transactional
	@Override
	public List<Weibo> getWeibosByUserAndGroup(String userid, String groupname,
			int firstIndex, int count) {
		User user=this.userDAO.getUserById(userid);
		Group group=null;
		if(groupname!=null){
			group=this.groupDAO.getGroupByName(groupname);
		}
		return this.weiboDAO.getWeibosByFilter(user, group, firstIndex, count);
	}

	@Transactional
	@Override
	public long getWeibosCountByUserAndGroup(String userid, String groupname) {
		User user=this.userDAO.getUserById(userid);
		Group group=null;
		if(groupname!=null){
			group=this.groupDAO.getGroupByName(groupname);
		}
		return this.weiboDAO.getWeibosCountByFilter(user, group);
	}

	@Override
	public Weibo getWeiboById(String id) {
		return this.weiboDAO.getWeiboById(id);
	}

	@Transactional
	@Override
	public List<Weibo> getWeiboByUser(User user, int firstIndex, int count) {
		return this.weiboDAO.getWeiboByUser(user, firstIndex, count);
	}

	@Transactional
	@Override
	public long getWeiboCountByUser(User user) {
		return this.weiboDAO.getWeiboCountByUser(user);
	}

}
