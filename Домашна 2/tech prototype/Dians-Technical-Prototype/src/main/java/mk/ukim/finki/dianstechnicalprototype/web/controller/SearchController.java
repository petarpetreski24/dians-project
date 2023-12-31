package mk.ukim.finki.dianstechnicalprototype.web.controller;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dianstechnicalprototype.model.User;
import mk.ukim.finki.dianstechnicalprototype.model.Winery;
import mk.ukim.finki.dianstechnicalprototype.service.WineryService;
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
                            @SessionAttribute User user,
                            Model model){
        model.addAttribute("bodyContent", "search-page");
        model.addAttribute("user",user);
        model.addAttribute("searchText",searchText);

        if (searchCriteria != null) {
            switch (searchCriteria) {
                case "name" -> {
                    List<Winery> wineries = wineryService.findByName(searchText);
                    model.addAttribute("wineries", wineries);
                }
                case "location" -> {
                    List<Winery> wineries = wineryService.findByLocation(searchText);
                    model.addAttribute("wineries", wineries);
                }
                case "address" -> {
                    List<Winery> wineries = wineryService.findByAddress(searchText);
                    model.addAttribute("wineries", wineries);
                }
                case "id" -> {
                    List<Winery> wineries = new ArrayList<>();
                    Optional<Winery> winery = wineryService.findById(Long.getLong(searchText));
                    winery.ifPresent(wineries::add);
                    model.addAttribute("wineries", wineries);
                }
                default -> {
                    List<Winery> wineries = wineryService.findByOccupation(searchText);
                    model.addAttribute("wineries", wineries);
                }
            }
        }
        return "master-template";
    }
}
