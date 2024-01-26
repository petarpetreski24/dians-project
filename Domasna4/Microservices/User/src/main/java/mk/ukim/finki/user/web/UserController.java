package mk.ukim.finki.user.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.user.model.User;
import mk.ukim.finki.vinodventuraapp.model.exceptions.*;
import mk.ukim.finki.user.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")

public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping({"/user/{username}"})
    public String returnUserInfo(@PathVariable String username) {
        User user = authService.getUser(username);
        return user.getName() + " " + user.getSurname();
    }
}

