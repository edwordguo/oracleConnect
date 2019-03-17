package com.huawei.post;

import com.sun.deploy.net.HttpRequest;

/**
 * @author rango
 * @date 2019/3/16 9:11
 */
public class MainPost {
    public static void main(String[] args) {
        //发送 GET 请求
//        String s= HttpPost.sendGet("http://localhost:80/Home/RequestString", "key=123&v=456");
//        System.out.println(s);

        //发送 POST 请求
        String sr=HttpPost.sendPost("http://write.blog.csdn.net/postedit","");
        System.out.println(sr);
    }
}
