package me.figochan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.figochan.entities.Picture;
import me.figochan.service.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/picture")
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(value = "/show",method=RequestMethod.GET)
	public void getImage(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) {
		System.out.println(id);
		FileInputStream fis = null;
	    response.setContentType("image/gif");
	    Picture picture=this.pictureService.getPictureById(id);
	    try {
	        OutputStream out = response.getOutputStream();
	        File file = new File(picture.getPath()+picture.getSavename());
	        fis = new FileInputStream(file);
	        byte[] b = new byte[fis.available()];
	        fis.read(b);
	        out.write(b);
	        out.flush();
	    } catch (Exception e) {
	         e.printStackTrace();
	    } finally {
	        if (fis != null) {
	            try {
	               fis.close();
	            } catch (IOException e) {
	            e.printStackTrace();
	        }   
	          }
	    }
	}
}
