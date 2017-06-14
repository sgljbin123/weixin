package com.gongzhonghao.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * WxCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_card")
public class Card implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User wxUser;
	private String plateProvince;
	private String plateChar;
	private String plateNumber;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Card() {
	}

	/** minimal constructor */
	public Card(User wxUser, Timestamp createTime) {
		this.wxUser = wxUser;
		this.createTime = createTime;
	}

	/** full constructor */
	public Card(User wxUser, String plateProvince, String plateChar,
			String plateNumber, Timestamp createTime) {
		this.wxUser = wxUser;
		this.plateProvince = plateProvince;
		this.plateChar = plateChar;
		this.plateNumber = plateNumber;
		this.createTime = createTime;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "openid", nullable = false,referencedColumnName="openid")
	public User getWxUser() {
		return this.wxUser;
	}

	public void setWxUser(User wxUser) {
		this.wxUser = wxUser;
	}

	@Column(name = "plateProvince", length = 3)
	public String getPlateProvince() {
		return this.plateProvince;
	}

	public void setPlateProvince(String plateProvince) {
		this.plateProvince = plateProvince;
	}

	@Column(name = "plateChar", length = 1)
	public String getPlateChar() {
		return this.plateChar;
	}

	public void setPlateChar(String plateChar) {
		this.plateChar = plateChar;
	}

	@Column(name = "plateNumber", length = 10)
	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", plateProvince="
				+ plateProvince + ", plateChar=" + plateChar + ", plateNumber="
				+ plateNumber + ", createTime=" + createTime + "]";
	}
	
	

}