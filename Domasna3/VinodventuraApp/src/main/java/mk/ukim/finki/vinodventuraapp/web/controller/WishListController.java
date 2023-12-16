package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.model.WishList;
import mk.ukim.finki.vinodventuraapp.service.WineryService;
import mk.ukim.finki.vinodventuraapp.service.WishListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String getWishList(Model model, @SessionAttribute(required = false) User user, HttpServletRequest request) {
        String lang = (String)request.getSession().getAttribute("lang");
        if (user == null){
            model.addAttribute("hasError", true);
            model.addAttribute("error", "You need to be logged in to do this action.");
            model.addAttribute("wineries",wineryService.findAll());
            if (lang.equals("mk")){
                model.addAttribute("bodyContent", "all-wineries-mk");
                return "master-template-mk";
            }
            else {
                model.addAttribute("bodyContent", "all-wineries-en");
                return "master-template-en";
            }
        }
        List<Winery> wineries = wishListService.getWishListForUser(user).getWineries();
        model.addAttribute("bodyContent", "wish-list");
        model.addAttribute("wineries", wineries);
        model.addAttribute("user", user);
        if (lang.equals("mk")){
            model.addAttribute("bodyContent", "all-wineries-mk");
            return "master-template-mk";
        }
        else {
            model.addAttribute("bodyContent", "all-wineries-en");
            return "master-template-en";
        }
    }

    @PostMapping("/add-winery/{id}")
    public String addWineryToWishList(@PathVariable Long id, @SessionAttribute(required = false) User user,
                                      Model model, @RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
        String lang = (String)request.getSession().getAttribute("lang");
        Page<Winery> wineryPage = wineryService.findAll(PageRequest.of(page, 5));
        if (user == null) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "You need to be logged in to do this action.");
            model.addAttribute("wineries", wineryPage.getContent());
            model.addAttribute("wineryPage", wineryPage);
            if (lang.equals("mk")){
                model.addAttribute("bodyContent", "all-wineries-mk");
                return "master-template-mk";
            }
            else {
                model.addAttribute("bodyContent", "all-wineries-en");
                return "master-template-en";
            }
        }
        try {
            WishList wishList = this.wishListService.addToWishlist(user, id);
            return "redirect:/allWineries?page=" + page;
        } catch (RuntimeException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("wineries", wineryPage.getContent());
            model.addAttribute("wineryPage", wineryPage);
            if (lang.equals("mk")){
                model.addAttribute("bodyContent", "all-wineries-mk");
                return "master-template-mk";
            }
            else {
                model.addAttribute("bodyContent", "all-wineries-en");
                return "master-template-en";
            }
        }
    }

    @PostMapping("/delete-winery/{id}")
    public String deleteProduct(@PathVariable Long id, @SessionAttribute User user, Model model, HttpServletRequest request) {
        if (user == null){
            model.addAttribute("hasError", true);
            model.addAttribute("error", "You need to be logged in to do this action.");
            model.addAttribute("wineries",wineryService.findAll());
            String lang = (String)request.getSession().getAttribute("lang");
            if (lang.equals("mk")){
                model.addAttribute("bodyContent", "all-wineries-mk");
                return "master-template-mk";
            }
            else {
                model.addAttribute("bodyContent", "all-wineries-en");
                return "master-template-en";
            }
        }

        this.wishListService.removeFromWishList(user, id);

        return "redirect:/wish-list";
    }


}
