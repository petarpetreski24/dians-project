package mk.ukim.finki.vinodventuraapp.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.repository.WineryRepository;
import mk.ukim.finki.vinodventuraapp.service.WineryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WineryServiceImpl implements WineryService {
    private final WineryRepository wineyRepository;
    @Override
    public List<Winery> findAll() {
        return wineyRepository.findAll();
    }

    @Override
    public Optional<Winery> findById(Long wineryId) {
        return wineyRepository.findById(wineryId);
    }

    @Override
    public List<Winery> findAllByLocation(String location) {
        return wineyRepository.findAllByLocation(location);
    }

    @Override
    public List<Winery> findAllByNameContaining(String name) {
        return wineyRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Winery> findByOccupationContaining(String occupation) {
        return wineyRepository.findAllByOccupationsContaining(occupation);
    }

    @Override
    public List<Winery> findAllByAddressContaining(String address) {
        return wineyRepository.findAllByAddressContaining(address);
    }
}
