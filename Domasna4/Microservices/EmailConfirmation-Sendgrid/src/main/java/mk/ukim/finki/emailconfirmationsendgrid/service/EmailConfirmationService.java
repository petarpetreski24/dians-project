package mk.ukim.finki.emailconfirmationsendgrid.service;

public interface EmailConfirmationService {
    public void sendConfirmationEmail(String toEmail, String confirmationLink);
}
