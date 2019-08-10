package com.bu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试是否连通
 */
@RestController
public class Hello {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello springboot!hell";
    }
}
