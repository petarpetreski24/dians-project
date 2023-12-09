package mk.ukim.finki.vinodventuraapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Winery {
    Long Id;
    String Name;
    String Address;
    String Location;
    String MobNumber;
    List<String> Occupations;
    String WorkingHours;
    String Website;
    Double Latitude;
    Double Longitude;
}
