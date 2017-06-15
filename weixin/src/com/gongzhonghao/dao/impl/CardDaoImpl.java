package com.gongzhonghao.dao.impl;

import org.springframework.stereotype.Repository;

import com.gongzhonghao.bean.Card;
import com.gongzhonghao.dao.CardDaoI;
@Repository("carDao")
public class CardDaoImpl extends BaseDaoImpl<Card> implements CardDaoI {

}
