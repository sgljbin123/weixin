package com.gongzhonghao.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.gongzhonghao.dto.AccessToken;
import com.gongzhonghao.dto.menu.Menu;

public class WeixinUtil {
    
    private static Logger log = Logger.getLogger(WeixinUtil.class);
    public static AccessToken accessToken;
    public static String appId;
    public static String appSecret;
    public static String token;
    
    // 获取access_token的接口地址（GET） 限200（次/天）
    public static String access_token_url;
    
    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url;
    public static String code_url;
    public static String moveCar_redirect_url;
    public static String js_access_token_url;
    static {
    	ResourceBundle rb = ResourceBundle.getBundle("wx");
    	appId = rb.getString("appId");
    	appSecret = rb.getString("appSecret");
    	token = rb.getString("token");
    	access_token_url = rb.getString("access_token_url");
    	menu_create_url = rb.getString("menu_create_url");
    	code_url = rb.getString("code_url");
    	moveCar_redirect_url = rb.getString("moveCar_redirect_url");
    	js_access_token_url = rb.getString("js_access_token_url");
    	try {
			code_url = code_url.replace("APPID", appId).replace("REDIRECT_URI", URLEncoder.encode(moveCar_redirect_url,"utf-8"))
					  .replaceAll("SCOPE", "snsapi_userinfo");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 创建菜单
     * 
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSON.toJSONString(menu);
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error(String.format("创建菜单失败 errcode:{%d} errmsg:{%s}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg")));
            }
        }

        return result;
    }
    
    public static void setIndustry(){
    	
    }
    
    /**
     * 获取access_token
     * 
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken() {
        String requestUrl = access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error(String.format("获取token失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg")));
            }
        }
        return accessToken;
    }
    //获取用户同意授权后返回的code，用于获取js调用的access_token.
    //和基础服务access_token不一样
    public static void getCode() throws UnsupportedEncodingException{
    	  String requestUrl = code_url.replace("APPID", appId).replace("REDIRECT_URI", URLEncoder.encode(moveCar_redirect_url,"utf-8"))
    			  .replaceAll("scope", "snsapi_userinfo");
          JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
          // 如果请求成功
//          if (null != jsonObject) {
//              try {
//                  accessToken = new AccessToken();
//                  accessToken.setToken(jsonObject.getString("access_token"));
//                  accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
//              } catch (JSONException e) {
//                  accessToken = null;
//                  // 获取token失败
//                  log.error(String.format("获取token失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg")));
//              }
//          }
    }
    
    /**
     * 描述:  发起https请求并获取结果
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSON.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }
	public static void main(String[] args) throws URISyntaxException {
    	try {
			System.out.println(URLEncoder.encode(moveCar_redirect_url,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
