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
        if (user == null) {
            handleNotLoggedInUser(model, wineryService.findAll(), request.getSession().getAttribute("lang"));
        } else {
            List<Winery> wineries = wishListService.getWishListForUser(user).getWineries();
            setWishListAttributes(model, wineries, user, request.getSession().getAttribute("lang"));
        }
        return determineMasterTemplate(request);
    }

    @PostMapping("/add-winery/{id}")
    public String addWineryToWishList(
            @PathVariable Long id,
            @SessionAttribute(required = false) User user,
            Model model,
            @RequestParam(defaultValue = "0") int page,
            HttpServletRequest request) {

        String lang = (String) request.getSession().getAttribute("lang");
        Page<Winery> wineryPage = wineryService.findAll(PageRequest.of(page, 5));

        if (user == null) {
            handleNotLoggedInUser(model, wineryPage.getContent(), lang);
        } else {
            try {
                wishListService.addToWishlist(user, id);
                return "redirect:/allWineries?page=" + page;
            } catch (RuntimeException exception) {
                handleWishListActionError(model, wineryPage.getContent(), lang, exception.getMessage());
            }
        }
        return determineMasterTemplate(request);
    }

    @PostMapping("/delete-winery/{id}")
    public String deleteWineryFromWishList(@PathVariable Long id, @SessionAttribute User user, Model model, HttpServletRequest request) {
        if (user == null) {
            handleNotLoggedInUser(model, wineryService.findAll(), request.getSession().getAttribute("lang"));
        } else {
            wishListService.removeFromWishList(user, id);
        }
        return "redirect:/wish-list";
    }

    private void handleNotLoggedInUser(Model model, List<Winery> wineries, Object langAttribute) {
        String lang = (langAttribute instanceof String) ? (String) langAttribute : "en"; // Default to "en" if lang is not set
        model.addAttribute("hasError", true);
        model.addAttribute("error", "You need to be logged in to do this action.");
        model.addAttribute("wineries", wineries);
        setLanguageSpecificAttributes(model, "all-wineries", lang);
    }

    private void handleWishListActionError(Model model, List<Winery> wineries, String lang, String errorMessage) {
        model.addAttribute("hasError", true);
        model.addAttribute("error", errorMessage);
        model.addAttribute("wineries", wineries);
        setLanguageSpecificAttributes(model, "all-wineries", lang);
    }

    private void setWishListAttributes(Model model, List<Winery> wineries, User user, Object langAttribute) {
        model.addAttribute("wineries", wineries);
        model.addAttribute("user", user);
        setLanguageSpecificAttributes(model, "wish-list", langAttribute);
    }

    private void setLanguageSpecificAttributes(Model model, String bodyContentSuffix, Object langAttribute) {
        String lang = (langAttribute instanceof String) ? (String) langAttribute : "en"; // Default to "en" if lang is not set
        String bodyContent = bodyContentSuffix + "-" + lang;
        model.addAttribute("bodyContent", bodyContent);
    }

    private String determineMasterTemplate(HttpServletRequest request) {
        String lang = (String) request.getSession().getAttribute("lang");
        return "master-template-" + lang;
    }
}
