package mk.ukim.finki.wineries.web;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wineries.model.Winery;
import org.springframework.web.bind.annotation.*;
import mk.ukim.finki.wineries.service.WineryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping({"/api/wineries"})
public class WineryController {

    private final WineryService wineryService;

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping("/show/{id}")
    public List<Winery> findWineryById(@PathVariable("id") Long wineryId) {
        List<Winery> wineries=new ArrayList<>();
        if(wineryService.findById(wineryId).isPresent())
        {
            wineries.add(wineryService.findById(wineryId).get());
        }
        return wineries;
    }
    @GetMapping("/all")
    public List<Winery> findAll() {
        return wineryService.findAll();
    }

    @GetMapping("/name/{name}")
    public List<Winery> findByName(@PathVariable("name") String winery) {
        return wineryService.findAllByNameContaining(winery);
    }

    @GetMapping("/location/{location}")
    public List<Winery> findByLocation(@PathVariable String location){
        return wineryService.findAllByLocation(location);
    }
    @GetMapping("/geoposition")
    public List<Winery> findAllByLongitudeLatitude(@RequestParam Double minLatitude,
                                                   @RequestParam Double maxLatitude,
                                                   @RequestParam Double minLongitude,
                                                   @RequestParam Double maxLongitude){
        return wineryService.findByLatitudeBetweenAndLongitudeBetween(minLatitude,maxLatitude,minLongitude,maxLongitude);
    }
}
