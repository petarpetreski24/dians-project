package mk.ukim.finki.dianstechnicalprototype.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.dianstechnicalprototype.model.User;
import mk.ukim.finki.dianstechnicalprototype.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.dianstechnicalprototype.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.dianstechnicalprototype.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = {"/","/login"})
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent", "login");
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;

        try {
            user = authService.login(request.getParameter("username"), request.getParameter("password"));
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {
            model.addAttribute("bodyContent", "login");
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "master-template";
        }

        request.getSession().setAttribute("user", user);
        return "redirect:/home";
    }
}

