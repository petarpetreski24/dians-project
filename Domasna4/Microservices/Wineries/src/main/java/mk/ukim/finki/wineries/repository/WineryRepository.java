package mk.ukim.finki.wineries.repository;

import mk.ukim.finki.wineries.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WineryRepository extends JpaRepository<Winery,Long> {
    List<Winery> findAllByLocationIgnoreCase(String location);

    List<Winery> findAllByNameContainingIgnoreCase(String name);

    List<Winery> findAllByOccupationsContainingIgnoreCase(String occupation);

    List<Winery> findAllByAddressContainingIgnoreCase(String address);
    List<Winery> findByLatitudeBetweenAndLongitudeBetween(
            @Param("minLatitude") Double minLatitude, @Param("maxLatitude") Double maxLatitude,
            @Param("minLongitude") Double minLongitude, @Param("maxLongitude") Double maxLongitude);

}
