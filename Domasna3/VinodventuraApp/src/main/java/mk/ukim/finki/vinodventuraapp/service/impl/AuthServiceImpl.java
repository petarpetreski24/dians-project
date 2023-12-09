package mk.ukim.finki.vinodventuraapp.service.impl;

import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.vinodventuraapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.vinodventuraapp.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.vinodventuraapp.repository.UserRepository;
import mk.ukim.finki.vinodventuraapp.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        User user = new User((long) (Math.random() * 1000),username, password, name, surname);
        return userRepository.saveOrUpdate(user);

    }
}

