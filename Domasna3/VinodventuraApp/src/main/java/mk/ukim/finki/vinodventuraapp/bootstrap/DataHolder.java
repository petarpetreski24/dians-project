package mk.ukim.finki.vinodventuraapp.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.model.WishList;
import mk.ukim.finki.vinodventuraapp.repository.UserRepository;
import mk.ukim.finki.vinodventuraapp.repository.WineryRepository;
import mk.ukim.finki.vinodventuraapp.repository.WishListRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final UserRepository userRepository;
    private final WineryRepository wineryRepository;
    private final WishListRepository wishListRepository;

    public DataHolder(UserRepository userRepository, WineryRepository wineryRepository, WishListRepository wishListRepository) {
        this.userRepository = userRepository;
        this.wineryRepository = wineryRepository;
        this.wishListRepository = wishListRepository;
    }

    @PostConstruct
    public void init(){
        List<User> users = new ArrayList<>();
        List<Winery> wineries = new ArrayList<>();
        List<WishList> wishLists = new ArrayList<>();

        if (userRepository.count() == 0){
            users.add(new User("pp","pp+","Petar","Petreski"));
            userRepository.saveAll(users);
        }

        if (wineryRepository.count() == 0){
            wineries.add(new Winery(
                    "Филовски винарија - Дирекција",
                    "11-ти Октомври 23-а-1/1",
                    "Скопје",
                    "38923133394",
                    "вина - производство, винарски визби",
                    "Пон - Пет од 08:00 до 16:00",
                    "http://www.filovski.com.mk",
                    41.99024133,
                    21.43550082
            ));

            wineries.add(new Winery(
                    "Винарија Попов",
                    "Гоце Делчев, с.Росоман 13-а",
                    "Кавадарци",
                    "38943551001",
                    "винарски визби, вина - производство",
                    "Пон - Пет од 07:00 до 16:00",
                    "http://www.popovwinery.com.mk",
                    41.43360138,
                    22.01239967
            ));

            wineries.add(new Winery(
                    "Кралска Винарија-Кралица Марија",
                    "Маршал Тито 63",
                    "Демир Капија",
                    "38943446500",
                    "винотеки (винарници), винарски визби, ресторани, апартмани и приватни соби - изнајмување, вина - производство",
                    "Пон - Нед од 00:00 до 00:00",
                    "http://www.wineryqueenmaria.com",
                    41.4097388,
                    22.24359005
            ));

            wineries.add(new Winery(
                    "Стоби Винарија",
                    "Автопат, с.Градско 2",
                    "Велес",
                    "38943215800",
                    "вина - производство, винарски визби, ресторани",
                    "Пон - Пет од 08:00 до 16:00",
                    "http://www.stobiwinery.mk",
                    41.71725082,
                    21.77546692
            ));

            wineries.add(new Winery(
                    "Лазар Винарија",
                    "Археолошки Локалитет Стоби Градско",
                    "Велес",
                    "38970315800",
                    "винарски визби, вина - производство, вина - трговија, апартмани и приватни соби - изнајмување, ресторани",
                    "Пон - Нед од 00:00 до 00:00",
                    "http://www.lazarwinery.mk/",
                    41.71725082,
                    21.77546692
            ));

            wineryRepository.saveAll(wineries);
        }

        //if (wishListRepository.count() == 0){

        //}
    }
}
