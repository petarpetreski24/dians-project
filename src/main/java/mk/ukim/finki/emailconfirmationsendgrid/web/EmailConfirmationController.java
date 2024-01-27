package mk.ukim.finki.emailconfirmationsendgrid.web;

import mk.ukim.finki.emailconfirmationsendgrid.model.EmailConfirmationRequest;
import mk.ukim.finki.emailconfirmationsendgrid.service.EmailConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailConfirmationController {

    @Autowired
    private EmailConfirmationService emailConfirmationService;

    @PostMapping("/email/confirm")
    public ResponseEntity<String> sendConfirmation(@RequestBody EmailConfirmationRequest confirmationRequest) {

        emailConfirmationService.sendConfirmationEmail(confirmationRequest.getEmail(), confirmationRequest.getConfirmationLink());

        return ResponseEntity.ok("Confirmation email sent successfully.");
    }
}
