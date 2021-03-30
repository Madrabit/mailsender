package ru.madrabit.mailsender;

import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class EmailGenerator {
    List<MimeMessage> messages = new ArrayList<>();

    private final JavaMailSender emailSender;
    private final EmailTemplate emailTemplate;
    private final ReadExcel excel;

    public EmailGenerator(JavaMailSender emailSender, EmailTemplate emailTemplate,
                          ReadExcel excel) throws MessagingException {
        this.emailSender = emailSender;
        this.emailTemplate = emailTemplate;
        this.excel = excel;
        this.emailTemplate.setTemplate();
        generator();
//        messages.add(createMessage("madrabot@gmail.com"));
//        messages.add(createMessage("hustle@inbox.ru"));
    }

    private void generator() throws MessagingException {
        final List<String> emails = excel.getEmails();
        for (String email : emails) {
            messages.add(createMessage(email));
        }
    }





    private MimeMessage createMessage(String to) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");

        String htmlMsg = emailTemplate.getTemplate();
        message.setHeader("Content-Type", "text/html; charset=utf-8");
        message.setContent(htmlMsg, "text/html; charset=utf-8");

//        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
        helper.setSubject("Заголовок");
        return message;
    }


}


