package com.minyazi.j2ee.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.minyazi.j2ee.core.util.LogUtil;

@Controller
public class HelloWorldController {
    @RequestMapping("/helloWorld")
    public ModelAndView helloWorld(){
        LogUtil.info("Hello, World!");
        return null;
    }
}
