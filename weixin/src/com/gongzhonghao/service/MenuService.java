package com.gongzhonghao.service;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class MenuService {
	public void createMenu(){
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=DNIxz62cNuesxbx0dr1hYf0TWHoV-gqqHJV_KMeJUUGTGoDdBLd9kBYTYr8ZyVU5p-h2tet0dlbGeQuirzUeeOOF_hao8umkjiUZpN-KAXt_zWlM-tV1oeyFUit1_GENUOBeAHAPJW";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
	}
}
