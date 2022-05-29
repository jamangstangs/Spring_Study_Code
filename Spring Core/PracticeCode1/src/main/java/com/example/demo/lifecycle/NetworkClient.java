package com.example.demo.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호츌, url= " + url);
        connect();
        call("초기화 연결 메시지");
    }

    private void call(String msg) {
        System.out.println("call = " +  url + " msg = " + msg);
    }

    private void connect() {
        System.out.println("connect = " + url);
    }

    public void disconnect() {
        System.out.println("close = " + url);
    }

    public void setURL(String url) {
        this.url = url;
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }
}
