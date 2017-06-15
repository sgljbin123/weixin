package com.gongzhonghao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gongzhonghao.bean.Card;
import com.gongzhonghao.dao.CardDaoI;

@Service
@Transactional
public class CardServiceImpl implements CardServiceI{
	private Logger logger = Logger.getLogger(CardServiceImpl.class);
	@Autowired
	private CardDaoI dao;
	@Override
	public Card get(Card card) {
		// TODO Auto-generated method stub
		List<Card> cards = dao.findByParam("from Card where plateProvince=:plateProvince and plateChar=:plateChar and plateNumber=:plateNumber",
				new String[]{"plateProvince","plateChar","plateNumber"},
				new String[]{card.getPlateProvince(),card.getPlateChar(),card.getPlateNumber()});
		if(cards.size()>0){
			return cards.get(0);
		}
		logger.info("获取车牌信息");
		return null;
	}

}
