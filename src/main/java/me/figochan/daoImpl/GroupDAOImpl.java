package me.figochan.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import me.figochan.dao.GroupDAO;
import me.figochan.entities.Group;
import me.figochan.entities.User;
@Component
public class GroupDAOImpl implements GroupDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addGroup(Group group) {
		this.hibernateTemplate.save(group);
		return true;
	}

	@Override
	public Group getGroupById(String id) {
		Group group=this.hibernateTemplate.get(Group.class, id);
		return group;
	}

	@Override
	public Group getGroupByName(String name) {
		@SuppressWarnings("unchecked")
		List<Group> list=this.hibernateTemplate.find("from Group where name=?",name);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteGroup(Group group) {
		this.hibernateTemplate.delete(group);
		return true;
	}

	@Override
	public List<Group> getGroupByUser(String userid) {
		@SuppressWarnings("unchecked")
		List<Group> list=this.hibernateTemplate.find("from Group where user.id=?",userid);
		return list;
	}

	@Override
	public Group getGroupByUserANdName(User user, String name) {
		List<Group> list=this.hibernateTemplate.find("from Group where user.id=? and name=?",user.getId(),name);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
