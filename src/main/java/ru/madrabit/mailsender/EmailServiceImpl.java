package ru.madrabit.mailsender;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
public class EmailServiceImpl {

    private final JavaMailSender emailSender;
    private final EmailGenerator emailGenerator;

    public EmailServiceImpl(JavaMailSender emailSender, EmailGenerator emailGenerator) {
        this.emailSender = emailSender;
        this.emailGenerator = emailGenerator;
    }

    @ResponseBody
    @RequestMapping("/send")
    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        List<MimeMessage> messages = emailGenerator.getMessages();
        for (MimeMessage message : messages) {
            emailSender.send(message);
        }
    }
}
