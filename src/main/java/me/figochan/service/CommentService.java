package me.figochan.service;

import java.util.List;

import me.figochan.entities.Comment;

public interface CommentService {
	public String addComment(Comment comment);
	public List<Comment> getCommentsByWeibo(String weiboid);
	public String updatePraiseCount(String id,int praiseCount);
	public Comment getCommentById(String id);
}
