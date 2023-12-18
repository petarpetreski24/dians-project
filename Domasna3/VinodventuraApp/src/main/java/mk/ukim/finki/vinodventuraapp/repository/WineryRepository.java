package mk.ukim.finki.vinodventuraapp.repository;

import mk.ukim.finki.vinodventuraapp.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineryRepository extends JpaRepository<Winery,Long> {
    List<Winery> findAllByLocation(String location);

    List<Winery> findAllByNameContaining(String name);

    List<Winery> findAllByOccupationsContaining(String occupation);

    List<Winery> findAllByAddressContaining(String address);


}
