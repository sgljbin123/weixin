package com.gongzhonghao.service;

import com.gongzhonghao.bean.MoveCarMessage;
import com.gongzhonghao.dao.MoveCarMessageDaoI;

public class MoveCarMessageServiceImpl implements MoveCarMessageServiceI{
	private MoveCarMessageDaoI messageDao;
	@Override
	public void saveMessage(MoveCarMessage message) {
		// TODO Auto-generated method stub
		messageDao.save(message);
	}
}
