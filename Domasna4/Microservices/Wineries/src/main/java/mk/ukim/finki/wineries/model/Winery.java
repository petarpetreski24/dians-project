package mk.ukim.finki.wineries.model;

import jakarta.persistence.*;
import lombok.Data;

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

    public String getNameEn() {
        return name;
    }

    public String getAddressEn() {
        return address;
    }

    public String getLocationEn() {
        return location;
    }

    public String getOccupationsEn() {
        return occupations;
    }

    public String getWorkingHoursEn() {
        return workingHours;
    }
}
