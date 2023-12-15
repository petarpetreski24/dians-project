package mk.ukim.finki.vinodventuraapp.web.controller;

import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.service.WineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/winery/details")
public class WineryDetailsController {

    @Autowired
    private WineryService wineryService;

    @GetMapping("/{id}")
    public String getWineryDetails(@PathVariable Long id, Model model, @RequestParam(required = false) String error) {
        Optional<Winery> winery = wineryService.findById(id);

        if (winery.isPresent()) {
            model.addAttribute("winery", winery.get());
            model.addAttribute("bodyContent", "winery-details");
            if (error != null && !error.isEmpty()){
                model.addAttribute("error", "You need to be signed in to post a review");
                model.addAttribute("hasError",true);
            }
        } else {
            model.addAttribute("error", "Winery not found.");
            model.addAttribute("hasError", true);
            model.addAttribute("bodyContent", "winery-details");
        }

        return "master-template";
    }
}
