package mk.ukim.finki.wineries.repository;

import mk.ukim.finki.wineries.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineryRepository extends JpaRepository<Winery,Long> {
    List<Winery> findAllByLocationIgnoreCase(String location);

    List<Winery> findAllByNameContainingIgnoreCase(String name);

    List<Winery> findAllByOccupationsContainingIgnoreCase(String occupation);

    List<Winery> findAllByAddressContainingIgnoreCase(String address);


}
