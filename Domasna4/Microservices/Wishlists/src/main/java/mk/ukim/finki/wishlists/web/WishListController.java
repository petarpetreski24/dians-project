package mk.ukim.finki.wishlists.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.user.model.User;
import mk.ukim.finki.wineries.model.Winery;
import mk.ukim.finki.wineries.service.WineryService;
import mk.ukim.finki.wishlists.service.WishListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/wish-list")
@AllArgsConstructor
public class WishListController {
    private final WishListService wishListService;
    private final WineryService wineryService;

    @GetMapping
    public List<Winery> getWishList(Model model, @SessionAttribute(required = false) User user, HttpServletRequest request) {
        List<Winery> wineries = null;
        if (user == null) {
            handleNotLoggedInUser(model, wineryService.findAll(), request.getSession().getAttribute("lang"));
        } else {
            wineries = wishListService.getWishListForUser(user).getWineries();
        }
        return wineries;
    }

    private void handleNotLoggedInUser(Model model, List<Winery> wineries, Object langAttribute) {
        String lang = (langAttribute instanceof String) ? (String) langAttribute : "en"; // Default to "en" if lang is not set
        model.addAttribute("hasError", true);
        model.addAttribute("error", "You need to be logged in to do this action.");
        model.addAttribute("wineries", wineries);
        setLanguageSpecificAttributes(model, "all-wineries", lang);
    }

    private void setLanguageSpecificAttributes(Model model, String bodyContentSuffix, Object langAttribute) {
        String lang = (langAttribute instanceof String) ? (String) langAttribute : "en"; // Default to "en" if lang is not set
        String bodyContent = bodyContentSuffix + "-" + lang;
        model.addAttribute("bodyContent", bodyContent);
    }
}
