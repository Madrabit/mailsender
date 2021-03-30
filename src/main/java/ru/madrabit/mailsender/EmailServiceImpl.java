package ru.madrabit.mailsender;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
@Api(description = "REST API description")
public class EmailServiceImpl {

    private final JavaMailSender emailSender;
    private final EmailGenerator emailGenerator;

    public EmailServiceImpl(JavaMailSender emailSender, EmailGenerator emailGenerator) {
        this.emailSender = emailSender;
        this.emailGenerator = emailGenerator;
    }

    @ApiOperation(value = "Send emails")
    @GetMapping("/send")
    public void sendSimpleMessage() throws MessagingException {
        List<MimeMessage> messages = emailGenerator.getMessages();
        for (MimeMessage message : messages) {
            emailSender.send(message);
        }
        System.out.println("Finished");
    }
}
