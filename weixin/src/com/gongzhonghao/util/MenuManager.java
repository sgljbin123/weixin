package com.gongzhonghao.util;

import org.apache.log4j.Logger;

import com.gongzhonghao.dto.AccessToken;
import com.gongzhonghao.dto.menu.Button;
import com.gongzhonghao.dto.menu.CommonButton;
import com.gongzhonghao.dto.menu.ComplexButton;
import com.gongzhonghao.dto.menu.Menu;

public class MenuManager {
	
private static Logger log = Logger.getLogger(MenuManager.class);

public static void main(String[] args) {
    // 调用接口获取access_token
    AccessToken at = WeixinUtil.getAccessToken();

    if (null != at) {
        // 调用接口创建菜单
    	System.out.println(getMenu());
        int result = WeixinUtil.createMenu(getMenu(), at.getToken());

        // 判断菜单创建结果
        if (0 == result)
            log.info("菜单创建成功！");
        else
            log.info("菜单创建失败，错误码：" + result);
    }
}

/**
 * 组装菜单数据
 * 
 * @return
 */
private static Menu getMenu() {
    CommonButton btn11 = new CommonButton();
    btn11.setName("扫描二维码");
    btn11.setType("scancode_waitmsg");
    btn11.setKey("11");

    CommonButton btn12 = new CommonButton();
    btn12.setName("信息填写");
    btn12.setType("view");
    btn12.setUrl("http://caiqinet.duapp.com/weixin/loadCheck");
    btn12.setKey("12");
//
//    CommonButton btn13 = new CommonButton();
//    btn13.setName("周边搜索");
//    btn13.setType("click");
//    btn13.setKey("13");

//    CommonButton btn14 = new CommonButton();
//    btn14.setName("历史上的今天");
//    btn14.setType("click");
//    btn14.setKey("14");

    CommonButton btn21 = new CommonButton();
    btn21.setName("移车通知");
    btn21.setType("view");
    btn21.setUrl(WeixinUtil.code_url);
    btn21.setKey("21");

    CommonButton btn22 = new CommonButton();
    btn22.setName("获取二维码");
    btn22.setType("click");
    btn22.setKey("22");
//
//    CommonButton btn23 = new CommonButton();
//    btn23.setName("美女电台");
//    btn23.setType("click");
//    btn23.setKey("23");
//
//    CommonButton btn24 = new CommonButton();
//    btn24.setName("人脸识别");
//    btn24.setType("click");
//    btn24.setKey("24");
//
//    CommonButton btn25 = new CommonButton();
//    btn25.setName("聊天唠嗑");
//    btn25.setType("click");
//    btn25.setKey("25");

    CommonButton btn31 = new CommonButton();
    btn31.setName("使用帮助");
    btn31.setType("click");
    btn31.setKey("31");

    CommonButton btn32 = new CommonButton();
    btn32.setName("在线客服");
    btn32.setType("click");
    btn32.setKey("32");

    CommonButton btn33 = new CommonButton();
    btn33.setName("小幽默");
    btn33.setType("click");
    btn33.setKey("33");

    
    /**
     * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
     */
    
    ComplexButton mainBtn1 = new ComplexButton();
    mainBtn1.setName("移车通知");
    //一级下有4个子菜单
    mainBtn1.setSub_button(new CommonButton[] { btn11,btn12 });

    
    ComplexButton mainBtn2 = new ComplexButton();
    mainBtn2.setName("注册");
    mainBtn2.setSub_button(new CommonButton[] { btn21, btn22 });

    
    ComplexButton mainBtn3 = new ComplexButton();
    mainBtn3.setName("帮助");
    mainBtn3.setSub_button(new CommonButton[] { btn31, btn32,btn33});

    
    /**
     * 封装整个菜单
     */
    Menu menu = new Menu();
    menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

    return menu;
}
}