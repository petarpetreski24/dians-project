package mk.ukim.finki.vinodventuraapp.repository;

import mk.ukim.finki.vinodventuraapp.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineryRepository extends JpaRepository<Winery,Long> {
    List<Winery> findAllByLocationIgnoreCase(String location);

    List<Winery> findAllByNameContainingIgnoreCase(String name);

    List<Winery> findAllByOccupationsContainingIgnoreCase(String occupation);

    List<Winery> findAllByAddressContainingIgnoreCase(String address);


}
