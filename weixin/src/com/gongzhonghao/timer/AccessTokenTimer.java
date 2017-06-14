package com.gongzhonghao.timer;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.gongzhonghao.dto.AccessToken;
import com.gongzhonghao.util.WeixinUtil;

public class AccessTokenTimer extends TimerTask{
   private Logger logger = Logger.getLogger(AccessTokenTimer.class);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		ResourceBundle rb = ResourceBundle.getBundle("wx");
		String appid = rb.getString("appId");
		String appsecret = rb.getString("appSecret");
		AccessToken accessToken = WeixinUtil.getAccessToken();
		logger.info(String.format("定时获取Access Token:appid=%s,appsecret=%s,accesstoken:"+accessToken,appid,appsecret));
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(AccessTokenTimer.class.newInstance(), new Date(),600000);
	}

}
