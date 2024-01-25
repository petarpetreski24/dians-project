package mk.ukim.finki.user.service;

import mk.ukim.finki.user.model.User;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname);
    User getUser(String username);
}

