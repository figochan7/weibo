package me.figochan.dao;

import java.util.List;

import me.figochan.entities.Message;

public interface MessageDAO {
	public boolean addMessage(Message message);
	public int getUnReadMessageCountByToId(String toid);
	public List<Message> getMessagesByToId(String toid);
	public boolean deleteMessage(Message message);
	public boolean updateIsRead(String id);
	
	public Message getMessageById(String id);
}
