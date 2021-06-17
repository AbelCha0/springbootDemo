package com.yisquare.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FowardGraphqlController {

    @RequestMapping(value = "/graphql",method = RequestMethod.POST)
    public void forwardGraphql(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){


    }
}
