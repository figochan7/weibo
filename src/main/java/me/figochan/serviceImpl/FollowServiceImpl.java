package me.figochan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.figochan.dao.FollowDAO;
import me.figochan.entities.Follow;
import me.figochan.entities.User;
import me.figochan.service.FollowService;
@Component
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowDAO followDAO;
	
	@Transactional
	@Override
	public String addFollow(Follow follow) {
		Follow temp=this.followDAO.getFollowByFollowAndFollowed(follow.getFollow().getId(), follow.getFollowed().getId());
		if(temp!=null){
			return "已经关注";
		}
		boolean result=this.followDAO.addFollow(follow);
		if(!result){
			return "关注失败";
		}
		return "success";
	}

	@Transactional
	@Override
	public String deleteFollow(Follow follow) {
		String result= this.followDAO.deleteFollow(follow);
		return result;
	}

	@Override
	public List<Follow> getFollowedByFollow(String followid) {
		return this.followDAO.getFollowedByFollow(followid);
	}

	@Override
	public List<Follow> getFollowedByFollowAndGroup(String followid,
			String groupid) {
		return this.followDAO.getFollowedByFollowAndGroup(followid, groupid);
	}

	@Override
	public List<Follow> getFansByFollowed(String followedid) {
		return this.followDAO.getFansByFollowed(followedid);
	}

	@Override
	public int getFollowedCountByFollow(String followid) {
		return this.followDAO.getFollowedCountByFollow(followid);
	}

	@Override
	public int getFansCountByFollowed(String followedid) {
		return this.followDAO.getFansCountByFollowed(followedid);
	}

	@Override
	public Follow getFollowById(String id) {
		return this.followDAO.getFollowById(id);
	}

	@Transactional
	@Override
	public Follow getFollowByFollowAndFollowed(User follow, User followed) {
		return this.followDAO.getFollowByFollowAndFollowed(follow.getId(), followed.getId());
	}

}
