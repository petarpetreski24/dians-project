package mk.ukim.finki.user.service.impl;

import mk.ukim.finki.user.model.User;
import mk.ukim.finki.user.repository.UserRepository;
import mk.ukim.finki.user.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new RuntimeException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, String email) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword == null ||
                repeatPassword.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty() || email == null) {
            throw new RuntimeException();
        }
        if (password.length() < 8) {
            throw new RuntimeException();
        }
        if (!password.equals(repeatPassword)) {
            throw new RuntimeException();
        }
        System.out.println(userRepository.findByUsername(username));
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException(username);
        }
        User user = new User(username, password, name, surname,email);
        return userRepository.save(user);

    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    @Override
    public Boolean checkUserExistsByUsername(String username) {
        try {
            this.getUser(username);
            return true;
        }
        catch (RuntimeException ex){
            return false;
        }
    }

    @Override
    public Boolean checkUserExistsByEmail(String email) {
        try {
            userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
            return true;
        }
        catch (RuntimeException ex){
            return false;
        }
    }

    @Override
    public List<String> getAllRegisteredUsernames() {
        return userRepository.findAll().stream().map(user -> user.getUsername()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllRegisteredEmails() {
        return userRepository.findAll().stream().map(user -> user.getEmail()).collect(Collectors.toList());
    }
}

