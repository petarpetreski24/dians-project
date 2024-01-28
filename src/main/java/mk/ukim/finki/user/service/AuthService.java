package mk.ukim.finki.user.service;

import mk.ukim.finki.user.model.User;

import java.util.List;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, String email);
    User getUser(String username);
    Boolean checkUserExistsByUsername(String username);
    Boolean checkUserExistsByEmail(String email);
    List<String> getAllRegisteredUsernames();
    List<String> getAllRegisteredEmails();
}

