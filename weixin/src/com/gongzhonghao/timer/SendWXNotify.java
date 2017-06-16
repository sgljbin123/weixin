package com.gongzhonghao.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gongzhonghao.bean.MoveCarMessage;
import com.gongzhonghao.service.MoveCarMessageServiceI;
@Component
public class SendWXNotify extends TimerTask{
	private static Logger logger = Logger.getLogger(SendWXNotify.class);
	@Autowired
	private MoveCarMessageServiceI moveCarMessageService;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<MoveCarMessage> messages = new ArrayList<MoveCarMessage>();
		messages = moveCarMessageService.list();
		if(null != messages && messages.size()>0){
			for(MoveCarMessage message:messages){
				logger.info(message);
			}
		}else{
			logger.info("最近5分钟没有需要发送的通知消息");
		}
	}
}

