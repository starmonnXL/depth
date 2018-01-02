package com.depth.cms.commons.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Http请求工具类
 */
public class HttpUtils {
    /**
     * 发送http请求方法，这个方法是基于Apache的HttpClient框架来实现的
     * @param url 发送请求的url地址 【必填】
     * @param method 请求方式，只可以是"get"和"post"两种方式 【必填】
     * @param params 发送post请求时要带的参数信息,get请求的参数直接拼接到url地址后面了，所有get请求不能传参
     * @return
     */
    public static JSONObject sendHttpRequest(String url,String method,JSONObject params) throws IOException {
//        if(StringUtils.isEmpty(url)){
//            throw BizException.INSTANCE.newInstance("必填参数【url】不能为空!");
//        }
//        if(StringUtils.isEmpty(method)){
//            throw BizException.INSTANCE.newInstance("必填参数【method】不能为空!");
//        }
//        if(!("get".equals(method.toLowerCase())) && !("post".equals(method.toLowerCase()))){
//            throw BizException.INSTANCE.newInstance("请求方式只能是get和post两种方式");
//        }

        //创建一个客户端工具
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse resp = null;
        if("get".equals(method.toLowerCase())){
            //创建一个get对象，传入url地址就可以
            HttpGet get = new HttpGet(url);
            //通过client.execute方法发起请求,在execute方法里面需要传入get对象
            resp = client.execute(get);
        }
        if("post".equals(method.toLowerCase())){
            //创建一个post对象，传入url地址就可以
            HttpPost post = new HttpPost(url);
            StringEntity s = new StringEntity(params.toString());
            //设置请求参数编码
            s.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
            s.setContentType("application/json");
            post.setEntity(s);
            //通过client.execute方法发起请求,在execute方法里面需要传入get对象
            resp = client.execute(post);
        }
        //获取请求发送返回的状态码
        int statusCode =  resp.getStatusLine().getStatusCode();
        //状态码在200-300之间都是正常的状态
        JSONObject result = null;
        //如果发送状态等于200才说明请求成功
        if(statusCode == HttpStatus.SC_OK){
            HttpEntity entity = resp.getEntity();
            String content = EntityUtils.toString(entity);
            result = JSON.parseObject(content);
        }
        return result;
    }
}
