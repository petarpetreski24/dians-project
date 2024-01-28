package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.vinodventuraapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        setCommonAttributes(user, request.getSession().getAttribute("lang"), "home", model);
        return determineMasterTemplate(request);
    }

    @GetMapping("/*")
    public String redirectToHome() {
        return "redirect:/home";
    }

    private void setCommonAttributes(User user, Object langAttribute, String bodyContentSuffix, Model model) {
        model.addAttribute("user", user);
        if (langAttribute == null){

        }
        String lang = (langAttribute instanceof String) ? (String) langAttribute : "en"; // Default to "en" if lang is not set
        model.addAttribute("bodyContent", bodyContentSuffix + "-" + lang);
    }

    private String determineMasterTemplate(HttpServletRequest request) {
        String lang = (String) request.getSession().getAttribute("lang");
        return "master-template-" + lang;
    }

}

