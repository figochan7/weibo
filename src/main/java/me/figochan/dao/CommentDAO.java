package me.figochan.dao;

import java.util.List;

import me.figochan.entities.Comment;

public interface CommentDAO {
	public boolean addComment(Comment comment);
	public List<Comment> getCommentsByWeibo(String weiboid);
	public boolean updatePraiseCount(String id,int praiseCount);
	public Comment getCommentById(String id);
}
