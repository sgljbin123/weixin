package com.gongzhonghao.listener;

import javax.servlet.ServletConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletConfigAware;

@Component
public class StartupListener implements ApplicationContextAware,ServletConfigAware,InitializingBean,ApplicationListener<ContextRefreshedEvent>{
	private Logger logger = Logger.getLogger(StartupListener.class);
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		logger.info("----------启动定时任务-----------");
//		Timer timer = new Timer();
//		timer.schedule(new AccessTokenTimer(), new Date(), 20*60*1000);
		logger.info("----------定时任务启动完毕-----------");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void setServletConfig(ServletConfig arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}

}
