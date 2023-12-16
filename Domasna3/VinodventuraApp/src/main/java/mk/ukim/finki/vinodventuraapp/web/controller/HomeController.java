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

        model.addAttribute("user",user);
        String lang = (String)request.getSession().getAttribute("lang");
        if (lang.equals("mk")){
            model.addAttribute("bodyContent", "home-mk");
            return "master-template-mk";
        }
        else{
            model.addAttribute("bodyContent", "home-en");
            return "master-template-en";
        }
    }
}

