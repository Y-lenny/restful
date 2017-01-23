package com.rest.controller;

import com.rest.util.url.UrlMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>欢迎页</br>
 *
 * @author lennylv
 * @version 1.0
 * @class WelcomeController
 * @date 2017-1-20 14:07
 * @since 1.0
 */
@Controller
@RequestMapping(value = UrlMapping.WELCOME)
public class WelcomeController {


    /**
     * <br>跳转欢迎页面</br>
     *
     * @param model
     * @return java.lang.String
     * @throws
     * @author lennylv
     * @date 2017-1-20 14:08
     * @version 1.0
     * @since 1.0
     */
    @RequestMapping(value = "/welcome")
    public String welcome(Model model) {

        model.addAttribute("welcome", "hello welcome");
        return "welcome";
    }

}
