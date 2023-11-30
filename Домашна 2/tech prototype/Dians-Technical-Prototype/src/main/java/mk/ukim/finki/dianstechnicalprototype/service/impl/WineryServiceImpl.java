package mk.ukim.finki.dianstechnicalprototype.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.dianstechnicalprototype.model.Winery;
import mk.ukim.finki.dianstechnicalprototype.repository.WineryRepository;
import mk.ukim.finki.dianstechnicalprototype.service.WineryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WineryServiceImpl implements WineryService {
    private final WineryRepository wineryRepository;
    @Override
    public List<Winery> findAll() {
        return wineryRepository.findAll();
    }

    @Override
    public Optional<Winery> findById(Long wineryId) {
        return wineryRepository.findById(wineryId);
    }

    @Override
    public List<Winery> findByLocation(String city) {
        return wineryRepository.findByCity(city);
    }

    @Override
    public List<Winery> findByName(String name) {
        return wineryRepository.findByName(name);
    }

    @Override
    public List<Winery> findByOccupation(String occupation) {
        return wineryRepository.findByOccupation(occupation);
    }

    @Override
    public List<Winery> findByAddress(String address) {
        return wineryRepository.findByAddress(address);
    }
}
