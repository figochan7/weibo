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
	@NamedQuery(name="getMessagesByToId",query="from Message m  where m.to.id=:toid order by isRead asc")
})
@NamedNativeQueries({
	@NamedNativeQuery(name="updateIsRead",query="update w_message set isRead=:isRead where id=:id",resultClass=Message.class)
})
@Entity
@Table(name="w_message")
public class Message {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fromid")
	private User from;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="toid")
	private User to;
	@Column
	private Date date;
	@Column
	private String content;
	@Column
	private int isRead;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public Message() {}
	public Message(User from, User to, Date date, String content, int isRead) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		this.content = content;
		this.isRead = isRead;
	}
	
}
