package me.figochan.service;

import me.figochan.entities.Picture;

public interface PictureService {
	public String addPicture(Picture picture);
	public Picture getPictureById(String id);
}
