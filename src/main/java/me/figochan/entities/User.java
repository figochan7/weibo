package me.figochan.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@NamedNativeQueries({
	@NamedNativeQuery(name="addWeiboCount",query="update w_user set weiboCount=:weiboCount where id=:id",resultClass=User.class),
	@NamedNativeQuery(name="addFansCount",query="update w_user set fansCount=:fansCount where id=:id",resultClass=User.class),
	@NamedNativeQuery(name="addFollowCount",query="update w_user set followCount=:followCount where id=:id",resultClass=User.class),
	@NamedNativeQuery(name="addMessageCount",query="update w_user set messageCount=:messageCount where id=:id",resultClass=User.class),
})
@Entity
@Table(name="w_user")
public class User {
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	private String id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String telephone;
	@Column
	private String email;
	@Column
	private Date birthday;
	@Column
	private String introduction;
	@Column
	private String avatar;
	@Column
	private int messageCount;
	@Column
	private int followCount;
	@Column
	private int fansCount;
	@Column
	private int weiboCount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	public int getFollowCount() {
		return followCount;
	}
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	public int getFansCount() {
		return fansCount;
	}
	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}
	public int getWeiboCount() {
		return weiboCount;
	}
	public void setWeiboCount(int weiboCount) {
		this.weiboCount = weiboCount;
	}
	public User() {}
	public User(String username, String password, String telephone,
			String email, Date birthday,String introduction, String avatar) {
		super();
		this.username = username;
		this.password = password;
		this.telephone = telephone;
		this.email = email;
		this.birthday = birthday;
		this.introduction=introduction;
		this.avatar = avatar;
	}
	public User(String username, String password, String telephone,
			String email, Date birthday, String introduction, String avatar,
			int messageCount, int followCount, int fansCount, int weiboCount) {
		super();
		this.username = username;
		this.password = password;
		this.telephone = telephone;
		this.email = email;
		this.birthday = birthday;
		this.introduction = introduction;
		this.avatar = avatar;
		this.messageCount = messageCount;
		this.followCount = followCount;
		this.fansCount = fansCount;
		this.weiboCount = weiboCount;
	}
	
	
}
