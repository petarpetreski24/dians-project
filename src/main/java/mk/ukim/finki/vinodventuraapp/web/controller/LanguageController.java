package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.http.HttpRequest;

@Controller
@RequestMapping("/language")
public class LanguageController {

    @GetMapping("/mk")
    public String setLanguageMacedonian(HttpServletRequest request){
        request.getSession().setAttribute("lang", "mk");
        return "redirect:/home";
    }

    @GetMapping("/en")
    public String setLanguageEnglish(HttpServletRequest request){
        request.getSession().setAttribute("lang","en");
        return "redirect:/home";
    }
}
