package com.gongzhonghao.dao.impl;

import org.springframework.stereotype.Repository;

import com.gongzhonghao.bean.User;
import com.gongzhonghao.dao.UserDaoI;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDaoI{

}
