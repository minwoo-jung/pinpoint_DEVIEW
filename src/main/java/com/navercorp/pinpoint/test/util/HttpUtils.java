package com.navercorp.pinpoint.test.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);


    public static String call0(String host, String path, Map<String, Object> paramMap, String cookie) {
        String uri = bindingUri(host, path);

        return call0(uri, paramMap, cookie);
    }

    public static String call0(String uri, Map<String, Object> paramMap, String cookie) {
        LOGGER.info("call uri:{}", uri);


        HttpClient httpClient = null;
        try {
            HttpGet get = new HttpGet(uri);
            if (cookie != null) {
                get.setHeader("Cookie", cookie);
            }
            get.setParams(getHttpParams());
            get.addHeader("Content-Type", "application/json;charset=UTF-8");

            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            httpClient = getHttpClient(getHttpParams());

            return httpClient.execute(get, responseHandler);
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            if (null != httpClient && null != httpClient.getConnectionManager()) {
                httpClient.getConnectionManager().shutdown();
            }
        }
    }

    private static String bindingUri(String host, String path) {
        String uri = "";
        if (path.startsWith("/")) {
            uri = host + path;
        } else {
            uri = host + "/" + path;
        }

        if (uri.startsWith("http://")) {
            return uri;
        }

        return "http://" + uri;
    }

    private static HttpClient getHttpClient(HttpParams params) {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

        SingleClientConnManager cm = new SingleClientConnManager(getHttpParams(), schemeRegistry);
        DefaultHttpClient httpClient = new DefaultHttpClient(cm, getHttpParams());
        httpClient.setParams(params);
        return httpClient;
    }

    private static HttpEntity getEntity(Map<String, Object> paramMap) throws UnsupportedEncodingException {
        if (paramMap.size() != 0) {
            // size가 0일때 호출하면 entity에 {}가 들어감.
            return new StringEntity(paramMap.toString(), "UTF-8");
        } else {
            return new StringEntity("", "UTF-8");
        }
    }

    private static HttpParams getHttpParams() {
        HttpParams params = new BasicHttpParams();
        params.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
        return params;
    }

}
