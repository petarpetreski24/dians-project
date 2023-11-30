package mk.ukim.finki.dianstechnicalprototype.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.dianstechnicalprototype.model.User;
import mk.ukim.finki.dianstechnicalprototype.model.Winery;
import mk.ukim.finki.dianstechnicalprototype.model.WishList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<User> users;
    public static List<Winery> wineries;
    public static List<WishList> wishLists;

    @PostConstruct
    public void init(){
        users = new ArrayList<>();
        wineries = new ArrayList<>();
        wishLists = new ArrayList<>();

        users.add(new User((long) (Math.random()*1000),"pp","pp+","Petar","Petreski"));

        wineries.add(new Winery(
                (long) (Math.random()*1000),
                "Филовски винарија - Дирекција",
                "11-ти Октомври 23-а-1/1",
                "Скопје",
                "38923133394",
                new ArrayList<>(List.of("вина - производство, винарски визби".split(","))),
                "Пон - Пет од 08:00 до 16:00",
                "http://www.filovski.com.mk",
                41.99024133,
                21.43550082
        ));

        wineries.add(new Winery(
                (long) (Math.random() * 1000),
                "Винарија Попов",
                "Гоце Делчев, с.Росоман 13-а",
                "Кавадарци",
                "38943551001",
                new ArrayList<>(List.of("винарски визби, вина - производство".split(","))),
                "Пон - Пет од 07:00 до 16:00",
                "http://www.popovwinery.com.mk",
                41.43360138,
                22.01239967
        ));

        wineries.add(new Winery(
                (long) (Math.random() * 1000),
                "Кралска Винарија-Кралица Марија",
                "Маршал Тито 63",
                "Демир Капија",
                "38943446500",
                new ArrayList<>(List.of("винотеки (винарници), винарски визби, ресторани, апартмани и приватни соби - изнајмување, вина - производство".split(","))),
                "Пон - Нед од 00:00 до 00:00",
                "http://www.wineryqueenmaria.com",
                41.4097388,
                22.24359005
        ));

        wineries.add(new Winery(
                (long) (Math.random() * 1000),
                "Стоби Винарија",
                "Автопат, с.Градско 2",
                "Велес",
                "38943215800",
                new ArrayList<>(List.of("вина - производство, винарски визби, ресторани".split(","))),
                "Пон - Пет од 08:00 до 16:00",
                "http://www.stobiwinery.mk",
                41.71725082,
                21.77546692
        ));

        wineries.add(new Winery(
                (long) (Math.random() * 1000),
                "Лазар Винарија",
                "Археолошки Локалитет Стоби Градско",
                "Велес",
                "38970315800",
                new ArrayList<>(List.of("винарски визби, вина - производство, вина - трговија, апартмани и приватни соби - изнајмување, ресторани".split(","))),
                "Пон - Нед од 00:00 до 00:00",
                "http://www.lazarwinery.mk/",
                41.71725082,
                21.77546692
        ));
    }
}
