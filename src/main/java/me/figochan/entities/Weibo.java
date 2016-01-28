package me.figochan.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
@NamedNativeQueries({
	@NamedNativeQuery(name="updateForwardCount",query="update w_weibo set forwardCount=:forwardCount where id=:id",resultClass=Weibo.class),
	@NamedNativeQuery(name="updatePraiseCount",query="update w_weibo set praiseCount=:praiseCount where id=:id",resultClass=Weibo.class),
	@NamedNativeQuery(name="updateCommentCount",query="update w_weibo set commentCount=:commentCount where id=:id",resultClass=Weibo.class)
})
@Entity
@Table(name="w_weibo")
public class Weibo {
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
	@Column
	private int praiseCount;
	@Column
	private int forwardCount;
	@Column
	private int commentCount;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="sourceid")
	private Weibo source;
	@OneToMany(mappedBy="weibo",fetch=FetchType.EAGER)
	private List<Picture> picList=new ArrayList<>();
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
	public int getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}
	public int getForwardCount() {
		return forwardCount;
	}
	public void setForwardCount(int forwardCount) {
		this.forwardCount = forwardCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public Weibo getSource() {
		return source;
	}
	public void setSource(Weibo source) {
		this.source = source;
	}
	public List<Picture> getPicList() {
		return picList;
	}
	public void setPicList(List<Picture> picList) {
		this.picList = picList;
	}
	public Weibo() {}
	public Weibo(User user, String content, Date date, int praiseCount,
			int forwardCount, int commentCount, Weibo source) {
		super();
		this.user = user;
		this.content = content;
		this.date = date;
		this.praiseCount = praiseCount;
		this.forwardCount = forwardCount;
		this.commentCount = commentCount;
		this.source = source;
	}
	public Weibo(User user, String content, Date date, int praiseCount,
			int forwardCount, int commentCount, Weibo source,
			List<Picture> picList) {
		super();
		this.user = user;
		this.content = content;
		this.date = date;
		this.praiseCount = praiseCount;
		this.forwardCount = forwardCount;
		this.commentCount = commentCount;
		this.source = source;
		this.picList = picList;
	}
}
