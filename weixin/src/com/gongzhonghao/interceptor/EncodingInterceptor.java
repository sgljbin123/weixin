package com.gongzhonghao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class EncodingInterceptor extends HandlerInterceptorAdapter{
	private static Logger logger = Logger.getLogger(EncodingInterceptor.class);
		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			// TODO Auto-generated method stub
			logger.info("设置字符集");
			request.setCharacterEncoding("utf-8");
			return true;
		}
	
}
