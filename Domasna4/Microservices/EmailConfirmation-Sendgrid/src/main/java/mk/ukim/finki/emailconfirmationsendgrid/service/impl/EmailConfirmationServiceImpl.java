package mk.ukim.finki.emailconfirmationsendgrid.service.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.Value;
import mk.ukim.finki.emailconfirmationsendgrid.service.EmailConfirmationService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {
//    @Value("${spring.sendgrid.api-key}")
    private String sendGridApiKey = "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";
    @Override
    public void sendConfirmationEmail(String toEmail, String confirmationLink) {
        Email from = new Email("vinodventura@outlook.com"); //TODO
        Email to = new Email(toEmail);
        String subject = "Confirm Your Registration";
        String confirm = "<a href=" + confirmationLink + ">Confirm Your Account</a>";
        Content content = new Content("text/html", "<p>Hello,</p>" +
                "<p>Thank you for registering with Vinodventura.</p>" +
                "<p>To complete your registration, please click the following link: </p>" + confirm
                + "<p>If you did not request this registration, please disregard this email. Your account will not be confirmed.</p>");

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
