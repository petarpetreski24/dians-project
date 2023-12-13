package mk.ukim.finki.vinodventuraapp.web.controller;

import lombok.AllArgsConstructor;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.service.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final WineryService wineryService;

    @GetMapping
    public String getSearch(@RequestParam(required = false)String searchCriteria,
                            @RequestParam(required = false)String searchText,
                            @SessionAttribute(required = false) User user,
                            Model model){
        model.addAttribute("bodyContent", "search-page");
        model.addAttribute("user",user);
        model.addAttribute("searchText",searchText);

        if (searchCriteria != null) {
            switch (searchCriteria) {
                case "name" -> {
                    List<Winery> wineries = wineryService.findAllByNameContaining(searchText);
                    model.addAttribute("wineries", wineries);
                }
                case "location" -> {
                    List<Winery> wineries = wineryService.findAllByLocation(searchText);
                    model.addAttribute("wineries", wineries);
                }
                case "address" -> {
                    List<Winery> wineries = wineryService.findAllByAddressContaining(searchText);
                    model.addAttribute("wineries", wineries);
                }
                case "id" -> {
                    List<Winery> wineries = new ArrayList<>();
                    Optional<Winery> winery = wineryService.findById(Long.getLong(searchText));
                    winery.ifPresent(wineries::add);
                    model.addAttribute("wineries", wineries);
                }
                default -> {
                    List<Winery> wineries = wineryService.findByOccupationContaining(searchText);
                    model.addAttribute("wineries", wineries);
                }
            }
        }
        return "master-template";
    }
}
