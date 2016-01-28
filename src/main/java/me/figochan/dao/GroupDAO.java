package me.figochan.dao;

import java.util.List;

import me.figochan.entities.Group;
import me.figochan.entities.User;

public interface GroupDAO {
	public boolean addGroup(Group group);
	public Group getGroupById(String id);
	public Group getGroupByName(String name);
	public Group getGroupByUserANdName(User user,String name);
	public List<Group> getGroupByUser(String userid);
	public boolean deleteGroup(Group group);
}
