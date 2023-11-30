package mk.ukim.finki.dianstechnicalprototype.service;

import mk.ukim.finki.dianstechnicalprototype.model.Winery;

import java.util.List;
import java.util.Optional;

public interface WineryService {
    List<Winery> findAll();
    Optional<Winery> findById(Long wineryId);
    List<Winery> findByLocation(String location);
    List<Winery> findByName(String name);
    List<Winery> findByOccupation(String occupation);
    List<Winery> findByAddress(String address);
}
