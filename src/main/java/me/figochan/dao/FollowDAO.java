package me.figochan.dao;

import java.util.List;

import me.figochan.entities.Follow;

public interface FollowDAO {
	public boolean addFollow(Follow follow);
	public String deleteFollow(Follow follow);
	public Follow getFollowById(String id);
	public List<Follow> getFollowedByFollow(String followid);
	public List<Follow> getFollowedByFollowAndGroup(String followid,String groupid);
	public List<Follow> getFansByFollowed(String followedid);
	public Follow getFollowByFollowAndFollowed(String followid,String followedid);
	public int getFollowedCountByFollow(String followid);
	public int getFansCountByFollowed(String followedid);
}
