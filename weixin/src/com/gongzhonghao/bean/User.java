package com.gongzhonghao.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * WxUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_user")
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String openid;
	private String username;
	private String telephone;
	private String verificationCode;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Integer userType;
	private String sex;
	private Integer notifyStart;
	private Integer notifyStop;
	private Set<Card> wxCards = new HashSet<Card>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String openid, Timestamp updateTime) {
		this.openid = openid;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public User(String openid, String username, String telephone,
			String verificationCode, Timestamp createTime,
			Timestamp updateTime, Integer userType, String sex,
			Integer notifyStart, Integer notifyStop, Set<Card> wxCards) {
		this.openid = openid;
		this.username = username;
		this.telephone = telephone;
		this.verificationCode = verificationCode;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.userType = userType;
		this.sex = sex;
		this.notifyStart = notifyStart;
		this.notifyStop = notifyStop;
		this.wxCards = wxCards;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "openid", nullable = false, length = 100)
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "username", length = 10)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "telephone", length = 11)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "verificationCode", length = 6)
	public String getVerificationCode() {
		return this.verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "userType")
	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Column(name = "sex", length = 6)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "notify_start")
	public Integer getNotifyStart() {
		return this.notifyStart;
	}

	public void setNotifyStart(Integer notifyStart) {
		this.notifyStart = notifyStart;
	}

	@Column(name = "notify_stop")
	public Integer getNotifyStop() {
		return this.notifyStop;
	}

	public void setNotifyStop(Integer notifyStop) {
		this.notifyStop = notifyStop;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "wxUser")
	public Set<Card> getWxCards() {
		return this.wxCards;
	}

	public void setWxCards(Set<Card> wxCards) {
		this.wxCards = wxCards;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", openid=" + openid + ", username="
				+ username + ", telephone=" + telephone + ", verificationCode="
				+ verificationCode + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", userType=" + userType
				+ ", sex=" + sex + ", notifyStart=" + notifyStart
				+ ", notifyStop=" + notifyStop + "]";
	}
	
	

}