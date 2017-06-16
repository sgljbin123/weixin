package com.gongzhonghao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gongzhonghao.bean.Card;
import com.gongzhonghao.bean.User;
import com.gongzhonghao.dto.JsAccessToken;
import com.gongzhonghao.dto.SystemParam;
import com.gongzhonghao.service.CardServiceI;
import com.gongzhonghao.service.CoreService;
import com.gongzhonghao.service.MoveCarMessageServiceI;
import com.gongzhonghao.service.MoveCarMessageServiceImpl;
import com.gongzhonghao.service.UserServiceI;
import com.gongzhonghao.util.SignCheckUtil;

@Controller
public class WeixinController {
	private static Logger logger = Logger.getLogger(WeixinController.class);
	@Autowired
	public UserServiceI userService;
	@Autowired
	public CardServiceI cardService;
	@Autowired
	public MoveCarMessageServiceI moveCarMessageService;
	@RequestMapping(value = "/wx", method = RequestMethod.GET)
	public void getRequest(SystemParam param, HttpServletResponse response) {
		System.out.println("request");
		// 接收微信服务器以Get请求发送的4个参数
		PrintWriter out;
		try {
			out = response.getWriter();
			if (SignCheckUtil.checkSignature(param.getSignature(),
					param.getTimestamp(), param.getNonce())) {
				out.print(param.getEchostr()); // 校验通过，原样返回echostr参数内容
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/wx", method = RequestMethod.POST)
	public void getPost(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 接收微信服务器以Get请求发送的4个参数
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 调用核心业务类接收消息、处理消息
		String respXml = CoreService.processRequest(request);
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.close();
	}

	@RequestMapping("/moveCar")
	public ModelAndView loadCheck(HttpServletRequest request,
			HttpSession session) {
		String openid = (String) session.getAttribute("openid");
		logger.info("openid:" + openid);
		ModelAndView mav = null;
		if (null == openid) {
			// 进入移车页面
			logger.info("code:" + request.getParameter("code") + ",state:"
					+ request.getParameter("state"));
			JsAccessToken jsAccessToken = CoreService.getJsAccessToken(request);
			logger.info("jsAccessToken:" + jsAccessToken);
			openid = jsAccessToken.getOpenid();
			openid = "1";
		}
//		if (null != openid) {
			User user = new User();
			user = userService.getUser(openid);
			session.setAttribute("openid",openid);
			if (null != user) {
				logger.info(user);
				mav = new ModelAndView("movecar");
			} else {
				// 进入注册页面
				mav = new ModelAndView("register");
				mav.addObject("openid", openid);
			}
//		} else {
//			mav = new ModelAndView("error");
//			mav.addObject("msg", "获取openid失败");
//		}
		return mav;
	}

	/**
	 * 用户注册
	 * */
	@RequestMapping("/registerUser")
	@ResponseBody
	public String register(User user,Card card,HttpSession session) {
	
		String msg = "";
		if (null != user.getOpenid()) {

			try {
//				card.setPlateProvince(new String(card.getPlateProvince()
//						.getBytes("iso-8859-1"), "utf-8"));
//				user.setUsername(new String(user.getUsername().getBytes(
//						"iso-8859-1"), "utf-8"));
				Set<Card> cards = new HashSet<Card>();
				card.setPlateNumber(card.getPlateNumber().toUpperCase());
				card.setWxUser(user);
				cards.add(card);
				user.setWxCards(cards);
				logger.info(user);
				logger.info(card);
				userService.saveUser(user);
				session.setAttribute("openid", user.getOpenid());
				msg = "success";
			} catch (Exception e) {
				logger.error(e);
				msg = "fail";
			}
		} else {
			msg = "fail";
		}
		return msg;
	}

	@RequestMapping("/sendVerificationCode")
	public void sendVerificationCode(HttpServletRequest request,
			HttpServletResponse response) {
		String telnumber = request.getParameter("telnumber");
		String openid = request.getParameter("openid");
		logger.info("telnumber:" + telnumber + ",openid:" + openid);

		// 发送短信验证码
		try {
			response.getWriter().print("success");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping("/sendmovecarmsg")
	@ResponseBody
	public String sendmovecarmsg(Card card,HttpSession session) {
		logger.info("发送移车信息");
		card.setPlateNumber(card.getPlateNumber().toUpperCase());
		logger.info(card);
		String openid = (String) session.getAttribute("openid");
		logger.info("openid:"+openid);
		if(moveCarMessageService.process(card, openid)){
			return "success";
		}else{
			return "false";
		}
	}

}
