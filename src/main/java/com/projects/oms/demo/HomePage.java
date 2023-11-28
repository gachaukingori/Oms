package com.projects.oms.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomePage {
    @GetMapping("/home")
    public String homepage(Model model){
        model.addAttribute("hello world");
        return "index";
    }
}
