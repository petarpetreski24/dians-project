package mk.ukim.finki.vinodventuraapp.model.helpers;

import java.util.UUID;

public class EmailConfirmationTokenGenerator {

    public static String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
