package com.xxl.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author better
 * @Date 2020/4/23 18:46
 * @Version 666
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    @GetMapping("login")
    public  String login(){
        return "customer";
    }
    public String register(){
        return  "index";
    }
    public  String list(){
        return  "list";
    }
}
