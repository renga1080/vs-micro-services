package com.ranga.vs.gateway;

import com.ranga.vs.util.VSUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients
public class GatewayApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        VSUtil.prepareLocal();
        SpringApplication.run(GatewayApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // return super.configure(builder);
        try {
            System.setProperty("HOST", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            throw new RuntimeException("Failed to find host name");
        }
        builder.profiles(System.getProperty("env"));
        return builder.sources(this.getClass());
    }
}
