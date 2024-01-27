package mk.ukim.finki.wineries.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wineries.model.helper.WineryParser;
import mk.ukim.finki.wineries.repository.WineryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataHolder {
    private final WineryParser wineryParser;
    private final WineryRepository wineryRepository;

    public DataHolder(WineryParser wineryParser, WineryRepository wineryRepository) {
        this.wineryParser = wineryParser;
        this.wineryRepository = wineryRepository;
    }

    @PostConstruct
    public void init(){

        if (wineryRepository.count() == 0) {
            wineryRepository.saveAll(wineryParser.parseJsonFile("static/data.json"));
        }
    }
}
