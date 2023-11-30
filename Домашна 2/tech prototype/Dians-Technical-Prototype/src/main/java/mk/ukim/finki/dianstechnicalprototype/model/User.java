package mk.ukim.finki.dianstechnicalprototype.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    Long Id;
    private String username;
    private String password;
    private String name;
    private String surname;
}

