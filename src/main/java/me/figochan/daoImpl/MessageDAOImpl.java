package me.figochan.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import me.figochan.dao.MessageDAO;
import me.figochan.entities.Comment;
import me.figochan.entities.Message;
@Component
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public int getUnReadMessageCountByToId(String toid) {
		long count = 0;
		@SuppressWarnings("unchecked")
		List<Long> list = this.hibernateTemplate.find("select count(*) from Message where to.id=? and isRead=?",toid,0);
		if(list!=null&&list.size()>0){
			count = list.get(0);
		}
		return (int) count;
	}

	@Override
	public List<Message> getMessagesByToId(String toid) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Message> list = session.getNamedQuery("getMessagesByToId")
			.setParameter("toid", toid).list();
		return list;
	}

	@Override
	public boolean addMessage(Message message) {
		this.hibernateTemplate.save(message);
		return true;
	}

	@Override
	public boolean deleteMessage(Message message) {
		this.hibernateTemplate.delete(message);
		return true;
	}

	@Override
	public boolean updateIsRead(String id) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int result = session.getNamedQuery("updateIsRead")
					.setParameter("isRead", 1)
					.setParameter("id", id)
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public Message getMessageById(String id) {
		return this.hibernateTemplate.get(Message.class, id);
	}

}
