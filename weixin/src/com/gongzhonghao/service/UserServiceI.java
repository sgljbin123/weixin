package com.gongzhonghao.service;

import com.gongzhonghao.bean.User;


public interface UserServiceI {
	public void saveUser(User u);
	public User getUser(String openid);
}
