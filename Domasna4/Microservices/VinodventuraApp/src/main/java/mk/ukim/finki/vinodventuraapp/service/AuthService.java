package mk.ukim.finki.vinodventuraapp.service;

import mk.ukim.finki.vinodventuraapp.model.User;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, String email);
}

