package ru.blinov.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER, ROLE_USER')")
    public String getUserInfoByName(Model model, @PathVariable("name") String name) {
        model.addAttribute("name", name);
        return "user-info";
    }

}
