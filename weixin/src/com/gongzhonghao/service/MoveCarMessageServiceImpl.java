package com.gongzhonghao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gongzhonghao.bean.Card;
import com.gongzhonghao.bean.MoveCarMessage;
import com.gongzhonghao.bean.User;
import com.gongzhonghao.dao.CardDaoI;
import com.gongzhonghao.dao.MoveCarMessageDaoI;
import com.gongzhonghao.dao.impl.MoveCarMessageDaoImpl;
import com.gongzhonghao.util.DateUtil;

@Service
@Transactional
public class MoveCarMessageServiceImpl implements MoveCarMessageServiceI{
	@Autowired
	private MoveCarMessageDaoI messageDao;
	@Autowired
	private CardDaoI cardDao;
	@Autowired
	private UserServiceI userService;
	@Override
	public void saveMessage(MoveCarMessage message) {
		// TODO Auto-generated method stub
		messageDao.save(message);
	}
	@Override
	public boolean process(Card card,String openid) {
		// TODO Auto-generated method stub
		Card c = null;
		MoveCarMessage message = new MoveCarMessage();
		List<Card> cards = cardDao.findByParam("from Card where plateProvince=:plateProvince and plateChar=:plateChar and plateNumber=:plateNumber",
				new String[]{"plateProvince","plateChar","plateNumber"},
				new String[]{card.getPlateProvince(),card.getPlateChar(),card.getPlateNumber()});
		if(cards.size()>0){
			c=cards.get(0);
			User user = userService.getUser(c.getWxUser().getOpenid());
			if(null!=user){
				message.setCreatetime(DateUtil.currentDate());
				message.setPlateChar(c.getPlateChar());
				message.setFromid(openid);
				message.setPlateNumber(c.getPlateNumber());
				message.setPlateProvince(c.getPlateProvince());
				message.setSendstatus(1);
				message.setToid(user.getOpenid());
				saveMessage(message);
				return true;
			}
		}
		return false;
	}
	@Override
	public List<MoveCarMessage> list() {
		// TODO Auto-generated method stub
		List<MoveCarMessage> moveCarMessages = new ArrayList<MoveCarMessage>();
		moveCarMessages = messageDao.query("from MoveCarMessage "
				+ "where createtime>=:lasttime"
				+ " and sendstatus=1",new String[]{"lasttime"},new String[]{DateUtil.format(DateUtil.getDate(-5))},1,100);
		return moveCarMessages;
	}
	
}
