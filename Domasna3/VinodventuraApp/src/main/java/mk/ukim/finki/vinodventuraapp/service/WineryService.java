package mk.ukim.finki.vinodventuraapp.service;

import mk.ukim.finki.vinodventuraapp.model.Winery;

import java.util.List;
import java.util.Optional;

public interface WineryService {
    List<Winery> findAll();
    Optional<Winery> findById(Long wineryId);
    List<Winery> findAllByLocation(String location);
    List<Winery> findAllByNameContaining(String name);
    List<Winery> findByOccupationContaining(String occupation);
    List<Winery> findAllByAddressContaining(String address);
}
