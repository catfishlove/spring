package com.example.boot03.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/list")
    public String getList(Model model) {
       
        List<String> names=new ArrayList()<String>();
        names.add("김구라");
        names.add("해골");
        names.add("원숭이");
       
        model.addAttribute("friends", names);
       
        return "friend/list";
    }
}