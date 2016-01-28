package me.figochan.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import me.figochan.dao.PictureDAO;
import me.figochan.entities.Picture;

@Component
public class PictureDAOImpl implements PictureDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addPicture(Picture picture) {
		this.hibernateTemplate.save(picture);
		return true;
	}

	@Override
	public Picture getPictureById(String id) {
		return this.hibernateTemplate.get(Picture.class, id);
	}

}
