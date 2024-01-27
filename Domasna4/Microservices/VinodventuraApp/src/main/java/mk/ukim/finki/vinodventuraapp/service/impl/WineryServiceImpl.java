package mk.ukim.finki.vinodventuraapp.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.vinodventuraapp.model.helpers.Translator;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import mk.ukim.finki.vinodventuraapp.repository.WineryRepository;
import mk.ukim.finki.vinodventuraapp.service.WineryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return wineyRepository.findAllByLocationIgnoreCase(Translator.getInstance().transliterateToCyrillic(location));
    }

    @Override
    public List<Winery> findAllByNameContaining(String name) {
        return wineyRepository.findAllByNameContainingIgnoreCase(Translator.getInstance().transliterateToCyrillic(name));
    }

    @Override
    public List<Winery> findByOccupationContaining(String occupation) {
        return wineyRepository.findAllByOccupationsContainingIgnoreCase(Translator.getInstance().transliterateToCyrillic(occupation));
    }

    @Override
    public List<Winery> findAllByAddressContaining(String address) {
        return wineyRepository.findAllByAddressContainingIgnoreCase(Translator.getInstance().transliterateToCyrillic(address));
    }

    @Override
    public Page<Winery> findAll(Pageable pageable) {
        return wineyRepository.findAll(pageable);
    }
}
