package com.ranga.vs.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserEndpoint {

    String VS_USER_SERVICE = "vs-user-service/api/v1/user";

    @RequestMapping(value = VS_USER_SERVICE, method = RequestMethod.GET)
    ResponseEntity<String> getTestUserName(@RequestParam String id);
}
