package com.gongzhonghao.listener;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletConfigAware;

import com.gongzhonghao.timer.SendWXNotify;

@Component
public class StartupListener implements ApplicationContextAware,ServletConfigAware,InitializingBean,ApplicationListener<ContextRefreshedEvent>{
	private Logger logger = Logger.getLogger(StartupListener.class);
	@Autowired
	private SendWXNotify send;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			logger.info("----------启动定时任务-----------");
			Timer timer = new Timer();
//			send.run();
//			timer.schedule(new AccessTokenTimer(), new Date(), 20*60*1000);
			timer.schedule(send, new Date(), 60*1000);
			logger.info("----------定时任务启动完毕-----------");	
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	@Override
	public void setServletConfig(ServletConfig arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	
		
	}

}
