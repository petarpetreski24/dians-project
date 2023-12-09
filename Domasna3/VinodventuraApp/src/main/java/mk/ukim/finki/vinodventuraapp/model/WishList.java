package mk.ukim.finki.vinodventuraapp.model;

import lombok.Data;
import mk.ukim.finki.vinodventuraapp.model.enumerations.WishListStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class WishList {
    private Long id;
    private LocalDateTime dateCreated;
    private User user;
    private List<Winery> wineries;
    private WishListStatus status;

    public WishList() {
        this.id = (long) (Math.random() * 1000);
    }

    public WishList(User user) {
        this.id = (long) (Math.random() * 1000);
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.wineries = new ArrayList<>();
        this.status = WishListStatus.CREATED;
    }
}

