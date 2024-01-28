package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.vinodventuraapp.model.EmailConfirmationRequest;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.exceptions.*;
import mk.ukim.finki.vinodventuraapp.model.helpers.EmailConfirmationTokenGenerator;
import mk.ukim.finki.vinodventuraapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

    private final AuthService authService;
    @Autowired
    private RestTemplate restTemplate;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

//    @GetMapping("/login")
//    public String getLoginPage(Model model, HttpServletRequest request) {
//        model.addAttribute("bodyContent", "home");
//        String lang = (String) request.getSession().getAttribute("lang");
//        return lang.equals("mk") ? "master-template-mk" : "master-template-en";
//    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        try {
            User user = authService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {
            handleLoginError(model, exception, request);
            return determineMasterTemplate(request);
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        request.getSession().invalidate();
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        String lang = (String) request.getSession().getAttribute("lang");
        String bodyContent = "register-" + lang;

        model.addAttribute("bodyContent", bodyContent);
        return "master-template-" + lang;
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String repeatPassword,
            @RequestParam String email,
            Model model,
            HttpServletRequest request) {

        try {
            authService.register(username, password, repeatPassword, name, surname,email);

            setSuccessAttributes(model, request);
            try{
                String token = EmailConfirmationTokenGenerator.generateToken();
                EmailConfirmationRequest confirmationRequest = new EmailConfirmationRequest(email,
                        "localhost:8080/home");
                restTemplate.postForEntity("http://localhost:9090/email/confirm", confirmationRequest, String.class);
            }
            catch (Exception exception){
                return determineMasterTemplate(request);
            }

        } catch (InvalidArgumentsException | PasswordsDoNotMatchException
                 | UsernameAlreadyExistsException | PasswordLengthException exception) {

            setErrorAttributes(model, exception, request);
            return determineMasterTemplate(request);

        }

        return determineMasterTemplate(request);
    }

    @GetMapping("/language/mk")
    public String setLanguageMacedonian(HttpServletRequest request){
        request.getSession().setAttribute("lang","mk");
        return "redirect:/home";
    }

    @GetMapping("/language/en")
    public String setLanguageEnglish(HttpServletRequest request){
        request.getSession().setAttribute("lang","en");
        return "redirect:/home";
    }

    private void handleLoginError(Model model, Exception exception, HttpServletRequest request) {
        model.addAttribute("hasError", true);
        model.addAttribute("error", exception.getMessage());
        String lang = (String) request.getSession().getAttribute("lang");
        model.addAttribute("bodyContent", "home-" + lang);
    }

    private String determineMasterTemplate(HttpServletRequest request) {
        String lang = (String) request.getSession().getAttribute("lang");
        return "master-template-" + lang;
    }
    private void setSuccessAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("hasError", true);
        String lang = (String) request.getSession().getAttribute("lang");
        model.addAttribute("error", getMessageForLanguage(lang, "Successfully registered! Please login."));
        model.addAttribute("bodyContent", "home-" + lang);
    }

    private void setErrorAttributes(Model model, Exception exception, HttpServletRequest request) {
        model.addAttribute("hasError", true);
        String lang = (String) request.getSession().getAttribute("lang");
        model.addAttribute("error", exception.getMessage());
        model.addAttribute("bodyContent", "register-" + lang);
    }

    private String getMessageForLanguage(String lang, String defaultMessage) {
        // Implement logic to fetch language-specific messages if needed
        return defaultMessage;
    }
}

