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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/allWineries")
@AllArgsConstructor
public class WineriesController {

    private final WineryService wineryService;

    @GetMapping
    public String showAllWineries(@RequestParam(required = false) String error,
                                  @SessionAttribute(required = false) User user, Model model,
                                  HttpServletRequest request){

        if(error!=null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Winery> wineries = wineryService.findAll();
        model.addAttribute("wineries",wineries);
        model.addAttribute("user",user);
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
}
