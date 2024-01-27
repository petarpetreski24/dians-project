package mk.ukim.finki.wineries.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wineries.model.Winery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import mk.ukim.finki.wineries.repository.WineryRepository;
import mk.ukim.finki.wineries.service.WineryService;

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
        return wineyRepository.findAllByLocationIgnoreCase(location);
    }

    @Override
    public List<Winery> findAllByNameContaining(String name) {
        return wineyRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Winery> findByOccupationContaining(String occupation) {
        return wineyRepository.findAllByOccupationsContainingIgnoreCase(occupation);
    }

    @Override
    public List<Winery> findAllByAddressContaining(String address) {
        return wineyRepository.findAllByAddressContainingIgnoreCase(address);
    }

    @Override
    public Page<Winery> findAll(Pageable pageable) {
        return wineyRepository.findAll(pageable);
    }

    @Override
    public List<Winery> findByLatitudeBetweenAndLongitudeBetween(Double minLatitude, Double maxLatitude, Double minLongitude, Double maxLongitude) {
        return wineyRepository.findByLatitudeBetweenAndLongitudeBetween(minLatitude,maxLatitude,minLongitude,maxLongitude);
    }
}
