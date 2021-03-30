package ru.madrabit.mailsender.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import ru.madrabit.mailsender.service.EmailSenderService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
@Api(description = "REST API description")
public class EmailSenderController {

    private final JavaMailSender emailSender;
    private final EmailSenderService emailSenderService;

    public EmailSenderController(JavaMailSender emailSender, EmailSenderService emailSenderService) {
        this.emailSender = emailSender;
        this.emailSenderService = emailSenderService;
    }

    @ApiOperation(value = "Send emails")
    @GetMapping("/send/{subject}")
    public void sendSimpleMessage(
            @ApiParam(name = "Subject",
                    required = true, example = "RE: hello")
            @PathVariable String subject) throws MessagingException {
        emailSenderService.setSubject(subject);
        emailSenderService.generate();
        List<MimeMessage> messages = emailSenderService.getMessages();
        for (MimeMessage message : messages) {
            emailSender.send(message);
        }
        System.out.println("Finished");
    }
}