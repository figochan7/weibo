package me.figochan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.figochan.dao.MessageDAO;
import me.figochan.entities.Message;
import me.figochan.service.MessageService;
@Component
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;
	
	@Transactional
	@Override
	public String addMessage(Message message) {
		boolean result = messageDAO.addMessage(message);
		if(result){
			return "success";
		}
		return "添加失败！请稍后再试！";
	}

	@Override
	public int getUnReadMessageCountByToId(String toid) {
		return this.messageDAO.getUnReadMessageCountByToId(toid);
	}

	@Transactional
	@Override
	public List<Message> getMessagesByToId(String toid) {
		return this.messageDAO.getMessagesByToId(toid);
	}

	@Transactional
	@Override
	public String deleteMessage(Message message) {
		boolean result=this.messageDAO.deleteMessage(message);
		if(result){
			return "success";
		}
		return "删除失败";
	}

	@Transactional
	@Override
	public String updateIsRead(String id) {
		boolean result=this.messageDAO.updateIsRead(id);
		if(result){
			return "success";
		}
		return "标记已读失败";
	}

	@Override
	public Message getMessageById(String id) {
		return this.messageDAO.getMessageById(id);
	}

}
