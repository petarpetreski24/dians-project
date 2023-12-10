package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.model.WishList;
import mk.ukim.finki.vinodventuraapp.service.WineryService;
import mk.ukim.finki.vinodventuraapp.service.WishListService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wish-list")
@AllArgsConstructor
public class WishListController {
    private final WishListService wishListService;
    private final WineryService wineryService;
    @GetMapping
    public String getWishList(@RequestParam(required = false) String error,
                              Model model, @SessionAttribute User user)
    {
        List<Winery> wineries = wishListService.getWishListForUser(user).getWineries();
        model.addAttribute("bodyContent", "wish-list");
        model.addAttribute("wineries",wineries);
        model.addAttribute("user",user);
        return "master-template";
    }
    @PostMapping("/add-winery/{id}")
    public String addWineryToWishList(@PathVariable Long id,@SessionAttribute User user,
                                      Model model)
    {
        List<Winery> wineries = wineryService.findAll();
        model.addAttribute("bodyContent", "all-wineries");
        model.addAttribute("wineries",wineries);
        try {
            WishList wishList = this.wishListService.addToWishlist(user,id);
            model.addAttribute("user",user);
            return "master-template";
        } catch (RuntimeException exception) {

            model.addAttribute("error", exception.getMessage());
            return "master-template";
        }
    }
    @PostMapping("/delete-winery/{id}")
    public String deleteProduct(@PathVariable Long id, @SessionAttribute User user, Model model) {

            this.wishListService.removeFromWishList(user,id);

            return "redirect:/wish-list";
        }


}
