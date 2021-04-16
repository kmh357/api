package com.rest.api.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@Controller
public class HelloController {

    @GetMapping("/helloworld/string")
    @ResponseBody
    public String helloWorldString(){
        return "helloworld";
    }


    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Hello helloworldJson(){
        Hello hello = new Hello();
        hello.message = "helloworld";
        return hello;
    }

    @GetMapping("/helloworld/page")
    public String helloworld(){
        return "helloworld";
    }

    @Getter
    @Setter
    public static class Hello{
        private String message;
    }
}
