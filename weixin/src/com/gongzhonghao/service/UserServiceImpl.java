package com.gongzhonghao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gongzhonghao.bean.User;
import com.gongzhonghao.dao.UserDaoI;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserServiceI{

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDaoI userDao;
	@Override
	public void saveUser(User u) {
		// TODO Auto-generated method stub
		String hql = "from User where openid=:openid";
		List<User> users = userDao.findByParam(hql, "openid", u.getOpenid());
		if(null == users||users.size()==0){
			userDao.save(u);
			logger.info("用户注册成功");
		}else{
			logger.info(users);
			u.setId(users.get(0).getId());
			userDao.merge(u);
		
		}
		
	}
	@Override
	public User getUser(String openid) {
		// TODO Auto-generated method stub
		String hql = "from User where openid=:openid";
		List<User> users = userDao.findByParam(hql, "openid", openid);
		User user = new User();
		if(null == users || users.size()==0){
			user = null;
		}else {
			user = users.get(0);
		}
		return user;
	}

}
