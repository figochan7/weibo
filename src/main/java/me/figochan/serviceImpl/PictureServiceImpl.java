package me.figochan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.figochan.dao.PictureDAO;
import me.figochan.entities.Picture;
import me.figochan.service.PictureService;

@Component
public class PictureServiceImpl implements PictureService {

	@Autowired
	private PictureDAO pictureDAO;
	
	@Transactional
	@Override
	public String addPicture(Picture picture) {
		boolean result = pictureDAO.addPicture(picture);
		if(result){
			return "success";
		}
		return "添加失败！请稍后再试！";
	}

	@Override
	public Picture getPictureById(String id) {
		return this.pictureDAO.getPictureById(id);
	}

}
