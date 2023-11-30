package mk.ukim.finki.dianstechnicalprototype.service;

import mk.ukim.finki.dianstechnicalprototype.model.User;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname);
}

