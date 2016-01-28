package me.figochan.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import me.figochan.dao.UserDAO;
import me.figochan.entities.User;
@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean saveUser(User user) {
		this.hibernateTemplate.save(user);
		return true;
	}

	@Override
	public User getUserById(String id) {
		User user=this.hibernateTemplate.get(User.class, id);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> list=this.hibernateTemplate.find("from User where username=?",username);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		User temp=this.getUserById(user.getId());
		
		if(temp==null){
			return false;
		}
		copyTo(user, temp);
		this.hibernateTemplate.update(temp);
		return true;
	}
	
	private void copyTo(User from, User to) {
		to.setUsername(from.getUsername());
		if(!from.getPassword().trim().equals("")){
			to.setPassword(from.getPassword());
		}
		to.setTelephone(from.getTelephone());
		to.setEmail(from.getEmail());
		to.setBirthday(from.getBirthday());
		to.setAvatar(from.getAvatar());
		to.setIntroduction(from.getIntroduction());
	}

	@Override
	public boolean addWeiboCount(User user) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int result = session.getNamedQuery("addWeiboCount")
					.setParameter("weiboCount", user.getWeiboCount()+1)
					.setParameter("id", user.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public boolean addFansCount(User user) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int result = session.getNamedQuery("addFansCount")
					.setParameter("fansCount", user.getFansCount()+1)
					.setParameter("id", user.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public boolean addFollowCount(User user) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int result = session.getNamedQuery("addFollowCount")
					.setParameter("followCount", user.getFollowCount()+1)
					.setParameter("id", user.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public boolean addMessageCount(User user) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int result = session.getNamedQuery("addMessageCount")
					.setParameter("messageCount", user.getMessageCount()+1)
					.setParameter("id", user.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public List<User> getHotUser() {
		@SuppressWarnings("unchecked")
		List<User> list = this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(User.class).addOrder(Order.desc("fansCount")),0,5);
		return list;
	}

	@Override
	public boolean updatePassword(User user, String password) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		session.createSQLQuery("update w_user a set a.password='"+password+"' where a.id='"+user.getId()+"';").executeUpdate();
		return true;
	}

	@Override
	public boolean minusWeiboCount(User user) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int weiboCount=user.getWeiboCount();
		if(weiboCount>=0){
			weiboCount-=1;
		}
		int result = session.getNamedQuery("addWeiboCount")
					.setParameter("weiboCount", weiboCount)
					.setParameter("id", user.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public boolean minusFansCount(User user) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int fansCount=user.getFansCount();
		if(fansCount>=0){
			fansCount-=1;
		}
		int result = session.getNamedQuery("addFansCount")
					.setParameter("fansCount", fansCount)
					.setParameter("id", user.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public boolean minusFollowCount(User user) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int followCount=user.getFollowCount();
		if(followCount>=0){
			followCount-=1;
		}
		int result = session.getNamedQuery("addFollowCount")
					.setParameter("followCount", followCount)
					.setParameter("id", user.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public boolean minusMessageCount(User user) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int messageCount=user.getMessageCount();
		if(messageCount>=0){
			messageCount-=1;
		}

		int result = session.getNamedQuery("addMessageCount")
					.setParameter("messageCount", messageCount)
					.setParameter("id", user.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

}
