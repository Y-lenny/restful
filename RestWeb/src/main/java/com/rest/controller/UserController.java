package com.rest.controller;

import com.google.common.collect.Lists;
import com.rest.util.url.UrlMapping;
import com.rest.bean.vo.UserVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <br>用户信息控制层</br>
 *
 * @author lennylv
 * @version 1.0
 * @class UserController
 * @date 2017-1-20 14:09
 * @since 1.0
 */
@Controller
@RequestMapping(value = UrlMapping.USERS)
public class UserController {

    @RequestMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {

        UserVo userVo = new UserVo();
        userVo.setUserId(id);
        model.addAttribute("userVo", userVo);
        return "user_info";
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserVo> getUserByName(@RequestParam(value = "username") String username, Model model) {

        UserVo userVo = new UserVo();
        userVo.setUsername(username);
        List<UserVo> userVos = Lists.newArrayList();
        userVos.add(userVo);
        model.addAttribute("userVos", userVos);
        return userVos;
    }

}
