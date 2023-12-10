package mk.ukim.finki.vinodventuraapp.web.controller;

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
    public String getRegisterPage(@RequestParam(required = false) String error, Model model)
    {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword) {
        try{
            this.authService.register(username, password, repeatPassword, name, surname);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException
                 | UsernameAlreadyExistsException | PasswordLengthException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}
