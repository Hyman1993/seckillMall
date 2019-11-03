package com.hyman.seckillMall.server.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: penghuang
 * @Date: 2019/10/19 22:11
 * @Version 1.0
 */
@Controller
@RequestMapping("base")
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    private static final String prefix="base";

    @RequestMapping(value="/welcome",method = RequestMethod.GET)
    public String welcome(ModelMap modelMap){
        String name = "";
        if(StringUtils.isBlank(name)) {
            name = "this is welcome";
        }
        modelMap.put("name",name);
        return "welcome";
    }

    @RequestMapping(value="/error",method=RequestMethod.GET)
    public String error(){
        return "error";
    }

}