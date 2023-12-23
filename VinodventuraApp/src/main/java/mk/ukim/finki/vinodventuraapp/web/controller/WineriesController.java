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

import java.util.List;

@Controller
@RequestMapping("/allWineries")
@AllArgsConstructor
public class WineriesController {

    private final WineryService wineryService;

    @GetMapping()
    public String showAllWineries(@RequestParam(required = false) String error,
                                           @SessionAttribute(required = false) User user, Model model,
                                           @RequestParam(defaultValue = "0") int page,
                                  HttpServletRequest request) {

        if(error!=null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        Page<Winery> wineryPage = wineryService.findAll(PageRequest.of(page,5));
        List<Winery> wineries = wineryService.findAll();
        model.addAttribute("wineries",wineryPage.getContent());
        model.addAttribute("user",user);
        model.addAttribute("wineryPage",wineryPage);
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
