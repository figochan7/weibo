package me.figochan.service;

import java.util.List;

import me.figochan.entities.Message;

public interface MessageService {
	public String addMessage(Message message);
	public int getUnReadMessageCountByToId(String toid);
	public List<Message> getMessagesByToId(String toid);
	public String deleteMessage(Message message);
	public String updateIsRead(String id);
	
	public Message getMessageById(String id);
}
