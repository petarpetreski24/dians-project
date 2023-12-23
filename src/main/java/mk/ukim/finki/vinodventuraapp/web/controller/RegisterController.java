package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.ukim.finki.vinodventuraapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.vinodventuraapp.model.exceptions.PasswordLengthException;
import mk.ukim.finki.vinodventuraapp.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.vinodventuraapp.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.vinodventuraapp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
@RequestMapping("/register")

public class RegisterController {
    private final AuthService authService;

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request)
    {

        String lang = (String)request.getSession().getAttribute("lang");
        if (lang.equals("mk")){
            model.addAttribute("bodyContent", "register-mk");
            return "master-template-mk";
        }
        else {
            model.addAttribute("bodyContent", "register-en");
            return "master-template-en";
        }
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           Model model,
                           HttpServletRequest request) {
        try{
            this.authService.register(username, password, repeatPassword, name, surname);

            model.addAttribute("hasError", true);

            String lang = (String)request.getSession().getAttribute("lang");
            if (lang.equals("mk")){
                model.addAttribute("error", "Успешно се регистриравте! Ве молиме најавете се.");
                model.addAttribute("bodyContent", "home-mk");
                return "master-template-mk";
            }
            else {
                model.addAttribute("error", "Successfully registered! Please login.");
                model.addAttribute("bodyContent", "home-en");
                return "master-template-en";
            }
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException
                 | UsernameAlreadyExistsException | PasswordLengthException exception) {

            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());

            String lang = (String)request.getSession().getAttribute("lang");
            if (lang.equals("mk")){
                model.addAttribute("bodyContent", "register-mk");
                return "master-template-mk";
            }
            else {
                model.addAttribute("bodyContent", "register-en");
                return "master-template-en";
            }
        }
    }

}
