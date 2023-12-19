package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.vinodventuraapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.vinodventuraapp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/login"})
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model, HttpServletRequest request) {
        model.addAttribute("bodyContent", "home");
        String lang = (String)request.getSession().getAttribute("lang");
        if (lang.equals("mk")){
            return "master-template-mk";
        }
        else return "master-template-en";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;

        try {
            user = authService.login(request.getParameter("username"), request.getParameter("password"));
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {

            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            String lang = (String)request.getSession().getAttribute("lang");
            if (lang.equals("mk")){
                model.addAttribute("bodyContent", "home-mk");
                return "master-template-mk";
            }
            else {
                model.addAttribute("bodyContent", "home-en");
                return "master-template-en";
            }
        }

        request.getSession().setAttribute("user", user);
        return "redirect:/home";
    }
}

