package ru.madrabit.mailsender.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.reader.HtmlReader;
import ru.madrabit.mailsender.reader.TemplateReader;
import ru.madrabit.mailsender.storage.EmailsDAO;
import ru.madrabit.mailsender.storage.ExcelEmailsDao;

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
    private final TemplateReader templateReader;
    private final EmailsDAO excel;
    private String subject = "RE:";

    public EmailSenderService(JavaMailSender emailSender, HtmlReader templateReader,
                              ExcelEmailsDao excel) {
        this.emailSender = emailSender;
        this.templateReader = templateReader;
        this.excel = excel;
    }

    public void generate() throws MessagingException {
        excel.readEmails();
        final List<String> emails = excel.getEmails();
        for (String email : emails) {
            messages.add(createMessage(email));
        }
    }

    private MimeMessage createMessage(String to) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        templateReader.readFile();
        String htmlMsg = templateReader.getTemplate();
        message.setHeader("Content-Type", "text/html; charset=utf-8");
        message.setContent(htmlMsg, "text/html; charset=utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        return message;
    }
}


