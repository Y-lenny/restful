package com.rest.restapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lennylv on 2017-1-3.
 */
@RestController
@RequestMapping(path="/v1/users")
public class UserController {

    @RequestMapping(path = "/{id}",method = RequestMethod.GET )
    public Object queryUserById(){
        return "test";
    }

}
