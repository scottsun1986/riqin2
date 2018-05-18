package com.fun4g.riqin.util;


import com.fun4g.riqin.iDao.IuserMapper;
import com.fun4g.riqin.model.Iuser;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URLEncoder;

/**
 * @author JackZhang
 */
public class OAuthAPI {
  /*  public static final String APP_ID = "wxc62d0e8e8641f451";
    public static final String APP_SECRET = "6e2bc2fdc977fef9caea23be927a479b";
    public static final String DOMAIN = "scottsun.ittun.com";*/
    /*现用的*/
   /* public static final String APP_ID = "wx45b6ea3d9f9eb681";
    public static final String APP_SECRET = "87ea9c1da65bb7518a809ad473b6445d";
    public static final String DOMAIN = "i.lebeacon.cn";
    */

    /*现用的第二个*/
    public static final String APP_ID = "wxc62d0e8e8641f451";
    public static final String APP_SECRET = "91ed691438fc5e61ad6c1b291d239791";
    public static final String DOMAIN = "i.lebeacon.cn";
/**/
    /*测试用8*、
    public static final String APP_ID = "wx403cee4264452cf7";
    public static final String APP_SECRET = "efb9b7b12ef856c78642fa7b4ac54f8f";
    public static final String DOMAIN = "scottsun.ittun.com";
*/
    //  public static final String DOMAIN = "scottsun.ittun.com";
    public static final IuserMapper iuserMapper = (IuserMapper) BeanGetter.getBean("iuserMapper");

    public static void OAuthIfNesscary(HttpServletRequest request,
                                       HttpServletResponse response) throws IOException {


        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        boolean isValidCode = true;
        String serviceUrl = URLEncoder.encode(
                "http://" + DOMAIN + request.getRequestURI(), "utf-8");
       // System.out.println(serviceUrl);
        //检查是否已验证或者验证是否通过
        if (code == null || code.equals("authdeny")) {
            isValidCode = false;
        }
        //如果session未空或者取消授权，重定向到授权页面
        if ((!isValidCode) && session.getAttribute("currentUser") == null) {
            StringBuilder oauth_url = new StringBuilder();
            //   https://api.weixin.qq.com/sns/oauth2/access_token?
            oauth_url.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
            oauth_url.append("appid=").append(APP_ID);
            oauth_url.append("&redirect_uri=").append(serviceUrl);
            oauth_url.append("&response_type=code");
            oauth_url.append("&scope=snsapi_userinfo");
            oauth_url.append("&state=1#wechat_redirect");
            response.sendRedirect(oauth_url.toString());
            return;
        }
        //如果用户同意授权并且，用户session不存在，通过OAUTH接口调用获取用户信息
        if (isValidCode && session.getAttribute("currentUser") == null) {
            Iuser currentUser = null;
            JSONObject obj = OAuthAPI.getAccessToken(OAuthAPI.APP_ID, OAuthAPI.APP_SECRET, code);
            String token = obj.getString("access_token");
            String openid = obj.getString("openid");
            currentUser = iuserMapper.selectByWxId(openid);
            System.out.println("from oauthapi:"+currentUser.getName());
            session.setAttribute("currentUser", currentUser);
        }
    }


    /**
     * 获取授权令牌
     */
    public static JSONObject getAccessToken(String appid, String secret,
                                            String code) {
        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        url.append("appid=" + appid);
        url.append("&secret=").append(secret);
        url.append("&code=").append(code);
        url.append("&grant_type=authorization_code");
        return JSONObject.fromObject(HttpRequest.sendPost(url.toString(), ""));
    }


}