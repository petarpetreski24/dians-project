package mk.ukim.finki.wineries.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wineries.model.Winery;
import mk.ukim.finki.wineries.repository.WineryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final WineryRepository wineryRepository;

    public DataHolder(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }

    @PostConstruct
    public void init(){

        if (wineryRepository.count() == 0) {
            wineryRepository.save(new Winery("ime", "adresa", "lokacija", "broj", "occ", "saati", "strana", 45.325, 44.25));
        }
    }
}
