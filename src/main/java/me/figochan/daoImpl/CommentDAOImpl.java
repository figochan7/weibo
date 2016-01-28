package me.figochan.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import me.figochan.dao.CommentDAO;
import me.figochan.entities.Comment;
import me.figochan.entities.Group;
@Component
public class CommentDAOImpl implements CommentDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addComment(Comment comment) {
		this.hibernateTemplate.save(comment);
		return true;
	}

	@Override
	public List<Comment> getCommentsByWeibo(String weiboid) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Comment> list = session.getNamedQuery("getCommentsByWeibo")
			.setParameter("weiboid", weiboid).list();
		return list;
	}

	@Override
	public boolean updatePraiseCount(String id, int praiseCount) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int result = session.getNamedQuery("updateCommentPraiseCount")
				.setParameter("praiseCount", praiseCount)
				.setParameter("id", id)
				.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public Comment getCommentById(String id) {
		Comment comment=this.hibernateTemplate.get(Comment.class, id);
		return comment;
	}

}
