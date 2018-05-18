package com.fun4g.riqin.util;

/**
 * Created by scottsun on 2016/6/30.
 */
public class MyToken {
    private MyToken() {}
    private  String token;
    private static MyToken instance  = new MyToken();
    public static MyToken getInstance() {
        return instance;
    }
    public  String getToken() {
        return token;
    }
    public  void setToken(String token) {
        this.token = token;
    }
}
