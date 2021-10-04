package ru.madrabit.mailsender.service.impl;

import com.google.common.io.ByteStreams;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.config.MailConfig;
import ru.madrabit.mailsender.reader.HtmlReader;
import ru.madrabit.mailsender.reader.TemplateReader;
import ru.madrabit.mailsender.service.EmailSenderService;
import ru.madrabit.mailsender.service.QueryService;
import ru.madrabit.mailsender.storage.EmailsDAO;
import ru.madrabit.mailsender.storage.ExcelEmailsDao;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {
    List<MimeMessage> messages = new ArrayList<>();

    private final JavaMailSender emailSender;
    private final TemplateReader templateReader;
    private final EmailsDAO excel;
    private String subject = "RE:";
    private final MailConfig mailConfig;
    private final QueryService queryServiceImpl;

    public EmailSenderServiceImpl(JavaMailSender emailSender, HtmlReader templateReader,
                                  ExcelEmailsDao excel, MailConfig mailConfig, QueryServiceImpl queryServiceImpl) {
        this.emailSender = emailSender;
        this.templateReader = templateReader;
        this.excel = excel;
        this.mailConfig = mailConfig;
        this.queryServiceImpl = queryServiceImpl;
    }

    @Override
    public void generate() throws MessagingException {
        messages.clear();
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
        helper.setFrom(mailConfig.getUsername());
        helper.setTo(to);
        helper.setSubject(subject);
        return message;
    }

    @Override
    public MimeMessage sendFile(List<Integer> deps, List<Float> orgTypes, String to) throws MessagingException {
        queryServiceImpl.getEmployeesByDepsOrgTypes(deps, orgTypes);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
       // message.setHeader("Content-Type", "text/html; charset=utf-8");
        helper.setFrom(mailConfig.getUsername());
        helper.setTo(to);
        helper.setSubject("выгрузка отделов");
        helper.setText("выгруженные отделы");
        helper.addAttachment("employees.xlsx", getFileFromDir().orElseThrow());
        return message;
    }



    private final static String SEPARATOR = File.separator;
    private static final String BASE_DIR = System.getProperty("user.dir") + SEPARATOR + "downloads" + SEPARATOR;

    private Optional<ByteArrayResource> getFileFromDir() {
        Optional<ByteArrayResource> resource = null;
        Path file = Paths.get(BASE_DIR, "employees.xlsx");
        try (InputStream inputStream = Files.newInputStream(file)) {
            resource = Optional.of(new ByteArrayResource(ByteStreams.toByteArray(inputStream)));
        } catch (IOException e) {
            log.error("IO exception getting.xlsx {}", e.getMessage());
        }
        return resource;
    }
}


