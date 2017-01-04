package com.rest.restapi.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lennylv on 2017-1-4.
 * TODO HTTP缓存、CACHE、RATE-LIMIT、HATEOAS、安全
 */
@RequestMapping(path = "/orders")
public class OrderController {

    /**
     * 通过username过滤用户
     * TODO ?embed=userVo.username
     *
     * @param
     * @return
     */

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Object queryUserRela(@PathVariable(value = "id") String id) {
        return null;
    }
}
