package ru.madrabit.mailsender.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.reader.HtmlReader;
import ru.madrabit.mailsender.reader.ExcelReader;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class EmailSenderService {
    List<MimeMessage> messages = new ArrayList<>();

    private final JavaMailSender emailSender;
    private final HtmlReader htmlReader;
    private final ExcelReader excel;
    private String subject = "RE:";

    public EmailSenderService(JavaMailSender emailSender, HtmlReader htmlReader,
                              ExcelReader excel) {
        this.emailSender = emailSender;
        this.htmlReader = htmlReader;
        this.excel = excel;
        this.htmlReader.setTemplate();
    }

    public void generate() throws MessagingException {
        final List<String> emails = excel.getEmails();
        for (String email : emails) {
            messages.add(createMessage(email));
        }
    }

    private MimeMessage createMessage(String to) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        String htmlMsg = htmlReader.getTemplate();
        message.setHeader("Content-Type", "text/html; charset=utf-8");
        message.setContent(htmlMsg, "text/html; charset=utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        return message;
    }
}


