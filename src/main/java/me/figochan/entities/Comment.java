package me.figochan.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@NamedQueries({
	@NamedQuery(name="getCommentsByWeibo",query="from Comment c where c.weibo.id=:weiboid")
})

@NamedNativeQueries({
	@NamedNativeQuery(name="updateCommentPraiseCount",query="update w_comment set praiseCount=:praiseCount where id=:id",resultClass=Comment.class)
})

@Entity
@Table(name="w_comment")
public class Comment {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userid")
	private User user;
	@Column
	private String content;
	@Column
	private Date date;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="weiboid")
	private Weibo weibo;
	@Column
	private int praiseCount;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Weibo getWeibo() {
		return weibo;
	}

	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}

	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Comment() {}

	public Comment(User user, String content, Date date, Weibo weibo,
			int praiseCount) {
		super();
		this.user = user;
		this.content = content;
		this.date = date;
		this.weibo = weibo;
		this.praiseCount = praiseCount;
	}
}
