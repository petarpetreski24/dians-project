package mk.ukim.finki.vinodventuraapp.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.model.helpers.WineryParser;
import mk.ukim.finki.vinodventuraapp.model.WishList;
import mk.ukim.finki.vinodventuraapp.repository.UserRepository;
import mk.ukim.finki.vinodventuraapp.repository.WineryRepository;
import mk.ukim.finki.vinodventuraapp.repository.WishListRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final UserRepository userRepository;
    private final WineryRepository wineryRepository;
    private final WishListRepository wishListRepository;
    private final WineryParser wineryParser;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(UserRepository userRepository, WineryRepository wineryRepository, WishListRepository wishListRepository, WineryParser wineryParser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.wineryRepository = wineryRepository;
        this.wishListRepository = wishListRepository;
        this.wineryParser = wineryParser;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init(){
        List<User> users = new ArrayList<>();
        List<Winery> wineries = new ArrayList<>();
        List<WishList> wishLists = new ArrayList<>();

        if (userRepository.count() == 0){
            users.add(new User("pp","pp+","Petar","Petreski","petarpetreski24@gmail.com"));
            userRepository.saveAll(users);
        }

        if (wineryRepository.count() == 0) {
            wineryRepository.saveAll(wineryParser.parseJsonFile("static/data.json"));
        }
    }
}
