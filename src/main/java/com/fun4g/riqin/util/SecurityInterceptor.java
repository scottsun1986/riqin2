package com.fun4g.riqin.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SecurityInterceptor implements HandlerInterceptor {
    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    private List<String> excludedUrls;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // intercept

        String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
            if (requestUri.endsWith(url)) {
                return true;
            }
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("currentUser") == null) {
           //测是    response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+OAuthAPI.APP_ID+"&redirect_uri=http://scottsun.ittun.com/riqin/wx_login&response_type=code&scope=snsapi_base&state=myjob#wechat_redirect");
           // response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx45b6ea3d9f9eb681&redirect_uri=http://i.lebeacon.cn/riqin/wx_login&response_type=code&scope=snsapi_base&state=myjob#wechat_redirect");

            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+OAuthAPI.APP_ID+"&redirect_uri=http://"+OAuthAPI.DOMAIN+"/riqin2/wx_login&response_type=code&scope=snsapi_base&state=myjob#wechat_redirect");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}