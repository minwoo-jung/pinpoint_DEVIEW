package com.navercorp.pinpoint.test.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.ch.Net;

@Controller
public class CallController {
	@RequestMapping("/call")
	@ResponseBody
	public String loginform() {
		call("http://www.naver.com", new HashMap<String, Object>(), "");
		call("http://" + NetUtils.getLocalV4Ip() + "/callSelf/getCurrentTimestamp.pinpoint", new HashMap<String, Object>(), "");
		return "success";
	}

	
    public String call(String uri, Map<String, Object> paramMap, String cookie) {
        if (null == uri) {
            return null;
        }

        HttpClient httpClient = null;
        try {
            HttpPost post = new HttpPost(uri);
            if (cookie != null) {
                post.setHeader("Cookie", cookie);
            }
            post.setEntity(getEntity(paramMap));
            post.setParams(getHttpParams());
            post.addHeader("Content-Type", "application/json;charset=UTF-8");

            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            httpClient = getHttpClient(getHttpParams());

            return httpClient.execute(post, responseHandler);
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        } finally {
            if (null != httpClient && null != httpClient.getConnectionManager()) {
                httpClient.getConnectionManager().shutdown();
            }
        }
    }
    
    private HttpClient getHttpClient(HttpParams params) {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

        SingleClientConnManager cm = new SingleClientConnManager(getHttpParams(), schemeRegistry);
        DefaultHttpClient httpClient = new DefaultHttpClient(cm, getHttpParams());
        httpClient.setParams(params);
        return httpClient;
    }
    
    private HttpEntity getEntity(Map<String, Object> paramMap) throws UnsupportedEncodingException {
        if (paramMap.size() != 0) {
            // size가 0일때 호출하면 entity에 {}가 들어감.
            return new StringEntity(paramMap.toString(), "UTF-8");
        } else {
            return new StringEntity("", "UTF-8");
        }
    }

    private HttpParams getHttpParams() {
        HttpParams params = new BasicHttpParams();
        params.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
        return params;
    }
}
