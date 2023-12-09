package mk.ukim.finki.vinodventuraapp.repository;

import mk.ukim.finki.vinodventuraapp.bootstrap.DataHolder;
import mk.ukim.finki.vinodventuraapp.model.Winery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class WineryRepository {

    public List<Winery> findAll(){
        return DataHolder.wineries;
    }
    public Optional<Winery> findById(Long wineryId){
        return DataHolder.wineries.stream().filter(x -> x.getId().equals(wineryId)).findFirst();
    }

    public List<Winery> findByCity(String city){
        return DataHolder.wineries.stream().filter(x -> x.getLocation().equals(city)).collect(Collectors.toList());
    }

    public List<Winery> findByName(String name){
        return DataHolder.wineries.stream().filter(x -> x.getName().contains(name)).collect(Collectors.toList());
    }

    public List<Winery> findByOccupation(String occupation){
        return DataHolder.wineries.stream().filter(x -> String.join(", ", x.getOccupations()).contains(occupation)).collect(Collectors.toList());
    }

    public List<Winery> findByAddress(String address){
        return DataHolder.wineries.stream().filter(x -> x.getAddress().contains(address)).collect(Collectors.toList());
    }

}
