package mk.ukim.finki.wineries.web;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wineries.model.Winery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mk.ukim.finki.wineries.service.WineryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wineries")
@Slf4j
public class WineryController {

    private final WineryService wineryService;

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping("/show/{id}")
    public List<Winery> findVeterinaryById(@PathVariable("id") Long hospitalId) {
        List<Winery> wineries=new ArrayList<>();
        if(wineryService.findById(hospitalId).isPresent())
        {
            wineries.add(wineryService.findById(hospitalId).get());
        }
        return wineries;
    }
    @GetMapping("/all")
    public List<Winery> findAll() {
        return wineryService.findAll();
    }

    @GetMapping("/name/{name}")
    public List<Winery> findByName(@PathVariable("name") String hospital) {

        return wineryService.findAllByNameContaining(hospital);
    }
}
