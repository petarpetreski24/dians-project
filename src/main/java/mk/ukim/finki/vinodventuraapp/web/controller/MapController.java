package mk.ukim.finki.vinodventuraapp.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.ukim.finki.vinodventuraapp.model.User;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.service.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = {"/map"})
@AllArgsConstructor
public class MapController {
    private final WineryService wineryService;

    @GetMapping
    public String getMap(HttpServletRequest request, Model model) {
        List<Winery> wineries = wineryService.findAll();
        User user = (User) request.getSession().getAttribute("user");

        model.addAttribute("footerMap", "true");
        model.addAttribute("user",user);
        model.addAttribute("wineries",wineries.toString());
        String lang = (String)request.getSession().getAttribute("lang");
        if (lang.equals("mk")){
            model.addAttribute("bodyContent", "map-mk");
            return "master-template-mk";
        }
        else {
            model.addAttribute("bodyContent", "map-en");
            return "master-template-en";
        }
    }
}
