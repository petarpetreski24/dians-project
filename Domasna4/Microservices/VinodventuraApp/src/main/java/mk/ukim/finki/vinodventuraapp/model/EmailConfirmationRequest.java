package mk.ukim.finki.vinodventuraapp.model;

import lombok.Data;

@Data
public class EmailConfirmationRequest {
    private String email;
    private String confirmationLink;

    public EmailConfirmationRequest(String email, String confirmationLink) {
        this.email = email;
        this.confirmationLink = confirmationLink;
    }
}
