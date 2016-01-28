package me.figochan.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="w_follow")
public class Follow {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="followid")
	private User follow;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="followedid")
	private User followed;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="groupid")
	private Group group;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getFollow() {
		return follow;
	}
	public void setFollow(User follow) {
		this.follow = follow;
	}
	public User getFollowed() {
		return followed;
	}
	public void setFollowed(User followed) {
		this.followed = followed;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Follow() {}
	public Follow(User follow, User followed, Group group) {
		super();
		this.follow = follow;
		this.followed = followed;
		this.group = group;
	}
}
