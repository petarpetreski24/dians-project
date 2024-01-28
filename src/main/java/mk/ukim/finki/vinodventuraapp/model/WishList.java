package mk.ukim.finki.vinodventuraapp.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.vinodventuraapp.model.enumerations.WishListStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "wishlists")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(name = "wishlist_wineries")
    private List<Winery> wineries;
    private WishListStatus status;

    public WishList() {

    }

    public WishList(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.wineries = new ArrayList<>();
        this.status = WishListStatus.CREATED;
    }
}

