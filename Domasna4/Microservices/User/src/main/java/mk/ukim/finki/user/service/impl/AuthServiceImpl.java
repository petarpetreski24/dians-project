package mk.ukim.finki.user.service.impl;

import mk.ukim.finki.user.model.User;
import mk.ukim.finki.vinodventuraapp.model.exceptions.*;
import mk.ukim.finki.user.repository.UserRepository;
import mk.ukim.finki.user.service.AuthService;
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
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword == null ||
                repeatPassword.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if (password.length() < 8) {
            throw new PasswordLengthException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        System.out.println(userRepository.findByUsername(username));
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(username, password, name, surname);
        return userRepository.save(user);

    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(InvalidArgumentsException::new);
    }
}

