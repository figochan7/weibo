package me.figochan.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import me.figochan.dao.FollowDAO;
import me.figochan.entities.Follow;
@Component
public class FollowDAOImpl implements FollowDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addFollow(Follow follow) {
		this.hibernateTemplate.save(follow);
		return true;
	}

	@Override
	public String deleteFollow(Follow follow) {
		try{
			this.hibernateTemplate.delete(follow);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，删除失败！";
		}
	}

	@Override
	public List<Follow> getFollowedByFollow(String followid) {
		@SuppressWarnings("unchecked")
		List<Follow> list = this.hibernateTemplate.find("from Follow f where f.follow.id=?",followid);
		return list;
	}

	@Override
	public List<Follow> getFollowedByFollowAndGroup(String followid,
			String groupid) {
		@SuppressWarnings("unchecked")
		List<Follow> list = this.hibernateTemplate.find("from Follow f where f.follow.id=? and f.group.id=?",followid,groupid);
		return list;
	}

	@Override
	public List<Follow> getFansByFollowed(String followedid) {
		@SuppressWarnings("unchecked")
		List<Follow> list = this.hibernateTemplate.find("from Follow f where f.followed.id=?",followedid);
		return list;
	}

	@Override
	public int getFollowedCountByFollow(String followid) {
		long count = 0;
		@SuppressWarnings("unchecked")
		List<Long> list = this.hibernateTemplate.find("select count(*) from Follow where follow.id=?",followid);
		if(list!=null&&list.size()>0){
			count = list.get(0);
		}
		return (int) count;
	}

	@Override
	public int getFansCountByFollowed(String followedid) {
		long count = 0;
		@SuppressWarnings("unchecked")
		List<Long> list = this.hibernateTemplate.find("select count(*) from Follow where followed.id=?",followedid);
		if(list!=null&&list.size()>0){
			count = list.get(0);
		}
		return (int) count;
	}

	@Override
	public Follow getFollowByFollowAndFollowed(String followid,
			String followedid) {
		@SuppressWarnings("unchecked")
		List<Follow> list = this.hibernateTemplate.find("from Follow f where f.follow.id=? and f.followed.id=?",followid,followedid);
		if(list==null || list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public Follow getFollowById(String id) {
		return this.hibernateTemplate.get(Follow.class, id);
	}

}
