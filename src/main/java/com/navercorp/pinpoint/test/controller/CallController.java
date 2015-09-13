package com.navercorp.pinpoint.test.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.navercorp.pinpoint.test.util.HttpUtils;
import com.navercorp.pinpoint.test.util.NetUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.ch.Net;

import javax.annotation.PostConstruct;

@Controller
public class CallController {


    @Value("#{pinpointDeviewProps['deview.server.ip'] ?: '127.0.0.1'}")
    private String ip;

    @Value("#{pinpointDeviewProps['deview.server.port'] ?: 80}")
    private int port;

    private String deviewAppHostAddress;

    @PostConstruct
    public void setUp() {
        if (ip.equals("127.0.0.1")) {
            deviewAppHostAddress = NetUtils.getLocalV4Ip();
        } else {
            deviewAppHostAddress = ip;
        }

        if (port != 80) {
            deviewAppHostAddress += ":" + port;
        }

        System.out.println(deviewAppHostAddress);
    }

	@RequestMapping("/call")
	@ResponseBody
	public String call() {
		HttpUtils.call0("http://www.naver.com", new HashMap<String, Object>(), "");
        return HttpUtils.call0(deviewAppHostAddress, "/callSelf/getCurrentTimestamp.pinpoint", new HashMap<String, Object>(), "");
	}

    @RequestMapping("/getGeoCode")
    @ResponseBody
    public String getGeoCode() {
        HttpUtils.call0("http://www.naver.com", new HashMap<String, Object>(), "");
        return HttpUtils.call0(deviewAppHostAddress, "/httpclient4/getGeoCode.pinpoint", new HashMap<String, Object>(), "");
    }

    @RequestMapping("/getTwitterUrlCount")
    @ResponseBody
    public String getTwitterUrlCount() {
        HttpUtils.call0("http://www.naver.com", new HashMap<String, Object>(), "");
        return HttpUtils.call0(deviewAppHostAddress, "/httpclient4/getTwitterUrlCount.pinpoint", new HashMap<String, Object>(), "");
    }

    @RequestMapping("/getTwitterUrlCountByPost")
    @ResponseBody
    public String getTwitterUrlCountByPost() {
        HttpUtils.call0("http://www.naver.com", new HashMap<String, Object>(), "");
        return HttpUtils.call0(deviewAppHostAddress, "/httpclient4/getTwitterUrlCountByPost.pinpoint", new HashMap<String, Object>(), "");
    }

}
