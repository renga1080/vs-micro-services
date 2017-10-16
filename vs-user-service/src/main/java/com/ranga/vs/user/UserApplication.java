package com.ranga.vs.user;

import com.ranga.vs.config.FilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static com.ranga.vs.util.VSUtil.prepareLocal;
import static com.ranga.vs.util.VSUtil.prepareTomcat;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackageClasses = com.ranga.vs.VsBasePackage.class)
@Import({FilterConfig.class})
public class UserApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        prepareLocal();
        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        prepareTomcat(builder);
        return builder.sources(this.getClass());
    }
}
