package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.service.WineryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class WineriesController {

    private final WineryService wineryService;

    @GetMapping("/allWineries")
    public String showAllWineries(
            @RequestParam(required = false) String error,
            @SessionAttribute(required = false) User user,
            Model model,
            @RequestParam(defaultValue = "0") int page,
            HttpServletRequest request) {

        setErrorMessageAttributes(error, model);
        setPageAttributes(page, model);
        setUserAttributes(user, model);

        String lang = (String) request.getSession().getAttribute("lang");
        setLanguageSpecificAttributes(lang, model);

        return determineMasterTemplate(request);
    }

    @GetMapping("/winery/details/{id}")
    public String getWineryDetails(
            @PathVariable Long id,
            Model model,
            @RequestParam(required = false) String error,
            HttpServletRequest request) {

        Optional<Winery> winery = wineryService.findById(id);
        String lang = (String) request.getSession().getAttribute("lang");

        if (winery.isPresent()) {
            setWineryAttributes(winery.get(), model);
            setErrorAttributes(error, model);
        } else {
            setErrorAttributes("Winery not found.", model);
            return redirectToHome(lang, model);
        }

        setLanguageSpecificAttributes(lang, model, "winery-details");

        return determineMasterTemplate(request);
    }

    @GetMapping("/search")
    public String getSearch(
            @RequestParam(required = false) String searchCriteria,
            @RequestParam(required = false) String searchText,
            @SessionAttribute(required = false) User user,
            Model model,
            HttpServletRequest request) {

        model.addAttribute("user", user);
        model.addAttribute("searchText", searchText);

        if (searchCriteria != null) {
            List<Winery> wineries = switch (searchCriteria) {
                case "name" -> wineryService.findAllByNameContaining(searchText);
                case "location" -> wineryService.findAllByLocation(searchText);
                case "address" -> wineryService.findAllByAddressContaining(searchText);
                case "id" -> {
                    List<Winery> singleWineryList = new ArrayList<>();
                    wineryService.findById(Long.getLong(searchText)).ifPresent(singleWineryList::add);
                    yield singleWineryList;
                }
                default -> wineryService.findByOccupationContaining(searchText);
            };
            model.addAttribute("wineries", wineries);
        }

        setLanguageSpecificAttributes((String) request.getSession().getAttribute("lang"), model, "search-page");

        return determineMasterTemplate(request);
    }
    @GetMapping("/map")
    public String getMap(HttpServletRequest request, Model model) {
        List<Winery> wineries = wineryService.findAll();
        User user = (User) request.getSession().getAttribute("user");

        setCommonAttributes(user, request.getSession().getAttribute("lang"), "map", model);
        model.addAttribute("footerMap", "true");
        model.addAttribute("wineries", wineries);

        return determineMasterTemplate(request);
    }
    private void setCommonAttributes(User user, Object langAttribute, String bodyContentSuffix, Model model) {
        model.addAttribute("user", user);
        String lang = (langAttribute instanceof String) ? (String) langAttribute : "en"; // Default to "en" if lang is not set
        model.addAttribute("bodyContent", bodyContentSuffix + "-" + lang);
    }


    private void setErrorMessageAttributes(String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
    }

    private void setPageAttributes(int page, Model model) {
        Page<Winery> wineryPage = wineryService.findAll(PageRequest.of(page, 5));
        model.addAttribute("wineries", wineryPage.getContent());
        model.addAttribute("wineryPage", wineryPage);
    }

    private void setUserAttributes(User user, Model model) {
        model.addAttribute("user", user);
    }

    private void setLanguageSpecificAttributes(String lang, Model model) {
        String bodyContent = "all-wineries-" + lang;
        model.addAttribute("bodyContent", bodyContent);
    }

    private String determineMasterTemplate(HttpServletRequest request) {
        String lang = (String) request.getSession().getAttribute("lang");
        return "master-template-" + lang;
    }
    private void setWineryAttributes(Winery winery, Model model) {
        model.addAttribute("winery", winery);
    }

    private void setErrorAttributes(String errorMessage, Model model) {
        if (errorMessage != null && !errorMessage.isEmpty()) {
            model.addAttribute("error", errorMessage);
            model.addAttribute("hasError", true);
        }
    }

    private String redirectToHome(String lang, Model model) {
        setErrorAttributes("Winery not found.", model);
        setLanguageSpecificAttributes(lang, model, "home");
        return determineMasterTemplate(lang, model);
    }

    private void setLanguageSpecificAttributes(String lang, Model model, String bodyContentSuffix) {
        String bodyContent = bodyContentSuffix + "-" + lang;
        model.addAttribute("bodyContent", bodyContent);
    }

    private String determineMasterTemplate(String lang, Model model) {
        model.addAttribute("bodyContent", "home-" + lang);
        return "master-template-" + lang;
    }
}
