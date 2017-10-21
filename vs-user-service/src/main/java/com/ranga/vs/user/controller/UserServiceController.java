package com.ranga.vs.user.controller;

import com.ranga.vs.endpoint.UserEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class UserServiceController implements UserEndpoint {

    @Value("${user.welcomeMsg}")
    //@Value("${vs.app.name}")
    private String welcomeMsg;

    @Override
    public ResponseEntity<String> getTestUserName(String id) {
        System.out.println("welcomeMsg" + welcomeMsg);
        return new ResponseEntity<String>(welcomeMsg, HttpStatus.OK);
    }
}
