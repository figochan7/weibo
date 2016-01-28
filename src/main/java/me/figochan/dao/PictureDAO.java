package me.figochan.dao;

import me.figochan.entities.Picture;

public interface PictureDAO {
	public boolean addPicture(Picture picture);
	public Picture getPictureById(String id);
}
