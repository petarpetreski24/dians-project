package mk.ukim.finki.vinodventuraapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@Table(name = "wineries")
public class Winery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String location;
    private String mobNumber;
    private String occupations;
    private String workingHours;
    private String website;
    private Double latitude;
    private Double longitude;
    @OneToMany(mappedBy = "winery")
    private List<Review> reviews;

    public Winery() {
    }

    public Winery(String name, String address, String location, String mobNumber, String occupations, String workingHours, String website, Double latitude, Double longitude) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.mobNumber = mobNumber;
        this.occupations = occupations;
        this.workingHours = workingHours;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
