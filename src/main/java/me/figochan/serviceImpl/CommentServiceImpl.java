package me.figochan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.figochan.dao.CommentDAO;
import me.figochan.entities.Comment;
import me.figochan.service.CommentService;

@Component
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;
	
	@Transactional
	@Override
	public String addComment(Comment comment) {
		boolean result=this.commentDAO.addComment(comment);
		if(result){
			return "success";
		}
		return "评论失败，稍后再试";
	}

	@Transactional
	@Override
	public List<Comment> getCommentsByWeibo(String weiboid) {
		return this.commentDAO.getCommentsByWeibo(weiboid);
	}

	@Transactional
	@Override
	public String updatePraiseCount(String id, int praiseCount) {
		boolean result= this.commentDAO.updatePraiseCount(id, praiseCount);
		if(result){
			return "success";
		}
		return "评论失败，稍后再试";
	}

	@Override
	public Comment getCommentById(String id) {
		return this.commentDAO.getCommentById(id);
	}

}
