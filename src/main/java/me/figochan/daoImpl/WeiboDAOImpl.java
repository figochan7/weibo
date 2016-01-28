package me.figochan.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import me.figochan.dao.WeiboDAO;
import me.figochan.entities.Group;
import me.figochan.entities.Picture;
import me.figochan.entities.User;
import me.figochan.entities.Weibo;
@Component
public class WeiboDAOImpl implements WeiboDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addWeibo(Weibo weibo) {
		this.hibernateTemplate.save(weibo);
		return true;
	}

	@Override
	public boolean updateCommentCount(Weibo weibo) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int result = session.getNamedQuery("updateCommentCount")
					.setParameter("commentCount", weibo.getCommentCount()+1)
					.setParameter("id", weibo.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePraiseCount(Weibo weibo) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int result = session.getNamedQuery("updatePraiseCount")
					.setParameter("praiseCount", weibo.getPraiseCount()+1)
					.setParameter("id", weibo.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public boolean updateForwardCount(Weibo weibo) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		int result = session.getNamedQuery("updateForwardCount")
					.setParameter("forwardCount", weibo.getForwardCount()+1)
					.setParameter("id", weibo.getId())
					.executeUpdate();
		if(result!=1){
			return false;
		}
		return true;
	}

	@Override
	public List<Weibo> getHotWeibo() {
		@SuppressWarnings("unchecked")
		List<Weibo> list = this.hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Weibo.class).addOrder(Order.desc("commentCount")),0,5);
		return list;
	}

	@Override
	public List<Weibo> getWeibosByFilter(User user, Group group,
			int firstIndex, int count) {
		//System.out.println(group.getName());
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql="";
		Query query=null;
		if(group==null){
			hql="select w from Weibo w,Follow f where f.follow=? and  w.user=f.followed or w.user=? order by w.date desc";
			query=session.createQuery(hql);
			query.setParameter(0, user);
			query.setParameter(1, user);
		}else{
			hql="select w from Weibo w,Follow f where f.follow=? and  w.user=f.followed and f.group=? or w.user=? order by w.date desc";
			query=session.createQuery(hql);
			query.setParameter(0, user);
			query.setParameter(1, group);
			query.setParameter(2, user);
		}
		query.setFirstResult(firstIndex);
		query.setMaxResults(count);
		
		List<Weibo> list=query.list();

		return list;
	}

	@Override
	public long getWeibosCountByFilter(User user, Group group) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql="";
		Query query=null;
		long count = 0;
		if(group==null){
			hql="select w from Weibo w,Follow f where f.follow=? and  w.user=f.followed or w.user=? order by w.date desc";
			query=session.createQuery(hql);
			query.setParameter(0, user);
			query.setParameter(1, user);
		}else{
			hql="select w from Weibo w,Follow f where f.follow=? and  w.user=f.followed and f.group=? or w.user=? order by w.date desc";
			query=session.createQuery(hql);
			query.setParameter(0, user);
			query.setParameter(1, group);
			query.setParameter(2, user);
		}

		
		List<Weibo> list=query.list();
		if(list!=null){
			count=list.size();
		}

		return count;
	}

	@Override
	public List<Weibo> getWeiboByUser(User user, int firstIndex, int count) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query=session.createQuery("from Weibo w where w.user=?");
		query.setParameter(0, user);
		query.setFirstResult(firstIndex);
		query.setMaxResults(count);
		
		List<Weibo> list=query.list();

		return list;
	}

	@Override
	public Weibo getWeiboById(String id) {
		return this.hibernateTemplate.get(Weibo.class, id);
	}

	@Override
	public long getWeiboCountByUser(User user) {
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		long count = 0;
		Query query=session.createQuery("from Weibo w where w.user=?");
		query.setParameter(0, user);
		
		List<Weibo> list=query.list();
		if(list!=null){
			count=list.size();
		}
		return list.size();
	}

}
