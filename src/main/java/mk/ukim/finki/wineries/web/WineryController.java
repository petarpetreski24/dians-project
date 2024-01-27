package mk.ukim.finki.wineries.web;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.wineries.model.Winery;
import org.springframework.web.bind.annotation.*;
import mk.ukim.finki.wineries.service.WineryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class WineryController {

    private final WineryService wineryService;

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }


    @GetMapping("/api/wineries/show/{id}")
    public List<Winery> findWineryById(@PathVariable("id") Long wineryId) {
        List<Winery> wineries=new ArrayList<>();
        if(wineryService.findById(wineryId).isPresent())
        {
            wineries.add(wineryService.findById(wineryId).get());
        }
        return wineries;
    }
    @GetMapping("/api/wineries/all")
    public List<Winery> findAll() {
        return wineryService.findAll();
    }

    @GetMapping("/api/wineries/name/{name}")
    public List<Winery> findByName(@PathVariable("name") String winery) {
        return wineryService.findAllByNameContaining(winery);
    }

    @GetMapping("/api/wineries/location/{location}")
    public List<Winery> findByLocation(@PathVariable String location){
        return wineryService.findAllByLocation(location);
    }
    @GetMapping("/api/wineries/geoposition")
    public List<Winery> findAllByLongitudeLatitude(@RequestParam Double minLatitude,
                                                   @RequestParam Double maxLatitude,
                                                   @RequestParam Double minLongitude,
                                                   @RequestParam Double maxLongitude){
        return wineryService.findByLatitudeBetweenAndLongitudeBetween(minLatitude,maxLatitude,minLongitude,maxLongitude);
    }
    @GetMapping("/*")
    public List<Winery> defaultWineries() {
        return wineryService.findAll();
    }
}
