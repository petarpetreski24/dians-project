package mk.ukim.finki.dianstechnicalprototype.web.controller;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dianstechnicalprototype.model.User;
import mk.ukim.finki.dianstechnicalprototype.model.Winery;
import mk.ukim.finki.dianstechnicalprototype.service.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/allWineries")
@AllArgsConstructor
public class WineriesController {

    private final WineryService wineryService;

    @GetMapping
    public String showAllWineries(@SessionAttribute User user, Model model){
        List<Winery> wineries = wineryService.findAll();
        model.addAttribute("bodyContent", "all-wineries");
        model.addAttribute("wineries",wineries);
        model.addAttribute("user",user);
        return "master-template";
    }
}
