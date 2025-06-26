package com.tienngv.security.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final Gson gson;

//    @GetMapping("/")
//    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
//        if (principal != null) {
//            log.info("[PRINCIPAL] [{}]", gson.toJson(principal));
//            model.addAttribute("name", principal.getAttribute("name"));
//            model.addAttribute("email", principal.getAttribute("email"));
//            model.addAttribute("picture", principal.getAttribute("picture"));
//        }
//        return "home";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
}
