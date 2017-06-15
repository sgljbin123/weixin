package com.gongzhonghao.dao.impl;

import org.springframework.stereotype.Repository;

import com.gongzhonghao.bean.MoveCarMessage;
import com.gongzhonghao.dao.MoveCarMessageDaoI;

@Repository("moveCarMessageDao")
public class MoveCarMessageDaoImpl extends BaseDaoImpl<MoveCarMessage> implements MoveCarMessageDaoI{

}
