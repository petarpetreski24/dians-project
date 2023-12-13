package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.vinodventuraapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/home", "/","*"})
public class HomeController {

    @GetMapping
    public String getHomePage(HttpServletRequest request,Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("bodyContent", "home");
        model.addAttribute("user",user);
        return "master-template";
    }
}

