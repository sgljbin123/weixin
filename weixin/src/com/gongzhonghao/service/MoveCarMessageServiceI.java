package com.gongzhonghao.service;

import java.util.List;

import com.gongzhonghao.bean.Card;
import com.gongzhonghao.bean.MoveCarMessage;

public interface MoveCarMessageServiceI {
	void saveMessage(MoveCarMessage message);
	boolean process(Card card,String openid);
	List<MoveCarMessage> list();
}
