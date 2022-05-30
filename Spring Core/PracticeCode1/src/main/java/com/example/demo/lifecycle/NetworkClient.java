package com.example.demo.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
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

    @PostConstruct
    public void init() {
        System.out.println("Network Client init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("Network Client Close");
        disconnect();
    }

}
