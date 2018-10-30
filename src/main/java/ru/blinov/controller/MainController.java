package ru.blinov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("favicon.ico")
    public String appFavicon() {
        return "forward:/resources/favicon.ico";
    }

}
