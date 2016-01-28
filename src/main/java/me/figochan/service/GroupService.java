package me.figochan.service;

import java.util.List;

import me.figochan.entities.Group;
import me.figochan.entities.User;

public interface GroupService {
	public String addGroup(Group group);
	public Group getGroupById(String id);
	public Group getGroupByName(String name);
	public Group getGroupByUserAndName(User user,String name);
	public List<Group> getGroupByUser(String userid);
	public String deleteGroup(Group group);
}
