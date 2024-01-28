package mk.ukim.finki.user.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.user.model.User;
import mk.ukim.finki.user.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping({"/{username}"})
    public String returnUserInfo(@PathVariable String username) {
        User user = authService.getUser(username);
        return user.getName() + " " + user.getSurname();
    }
    @GetMapping("checkU/{username}")
    public Boolean checkUserExistsUsername(@PathVariable String username) {
        return authService.checkUserExistsByUsername(username);
    }
    @GetMapping("/checkE/{email}")
    public Boolean checkUserExistsEmail(@PathVariable String email) {
        return authService.checkUserExistsByUsername(email);
    }
    @GetMapping("/allUsernames")
    public List<String> getAllRegisteredUsernames(){
        return authService.getAllRegisteredUsernames();
    }

    @GetMapping("/allEmails")
    public List<String> getAllRegisteredEmails(){
        return authService.getAllRegisteredEmails();
    }
}

