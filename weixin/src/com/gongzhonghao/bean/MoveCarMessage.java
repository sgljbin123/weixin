package com.gongzhonghao.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="wx_movecarmessage")
public class MoveCarMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fromid;
	private String toid;
	private String createtime;
	private String sendtime;
	private int sendstatus;
	private String plateProvince;
	private String plateChar;
	private String plateNumber;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromid() {
		return fromid;
	}
	public void setFromid(String fromid) {
		this.fromid = fromid;
	}
	public String getToid() {
		return toid;
	}
	public void setToid(String toid) {
		this.toid = toid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public int getSendstatus() {
		return sendstatus;
	}
	public void setSendstatus(int sendstatus) {
		this.sendstatus = sendstatus;
	}

	public String getPlateNumber() {
		return plateNumber;
	}
	public String getPlateProvince() {
		return plateProvince;
	}
	public void setPlateProvince(String plateProvince) {
		this.plateProvince = plateProvince;
	}
	public String getPlateChar() {
		return plateChar;
	}
	public void setPlateChar(String plateChar) {
		this.plateChar = plateChar;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	@Override
	public String toString() {
		return "MoveCarMessage [id=" + id + ", fromid=" + fromid + ", toid="
				+ toid + ", createtime=" + createtime + ", sendtime="
				+ sendtime + ", sendstatus=" + sendstatus + ", plateProvince="
				+ plateProvince + ", plateChar=" + plateChar + ", plateNumber="
				+ plateNumber + "]";
	}
	
}
