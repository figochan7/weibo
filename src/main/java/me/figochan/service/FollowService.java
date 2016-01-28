package me.figochan.service;

import java.util.List;

import me.figochan.entities.Follow;
import me.figochan.entities.User;

public interface FollowService {
	public String addFollow(Follow follow);
	public String deleteFollow(Follow follow);
	public List<Follow> getFollowedByFollow(String followid);
	public List<Follow> getFollowedByFollowAndGroup(String followid,String groupid);
	public List<Follow> getFansByFollowed(String followedid);
	
	public int getFollowedCountByFollow(String followid);
	public int getFansCountByFollowed(String followedid);
	
	public Follow getFollowById(String id);
	public Follow getFollowByFollowAndFollowed(User follow,User followed);
}
