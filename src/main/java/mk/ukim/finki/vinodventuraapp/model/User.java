package mk.ukim.finki.vinodventuraapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name="app_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    @OneToOne(mappedBy = "user")
    private WishList wishList;

    public User() {
    }

    public User(String username, String password, String name, String surname, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }


}

