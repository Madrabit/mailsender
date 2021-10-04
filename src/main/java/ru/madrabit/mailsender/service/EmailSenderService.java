package ru.madrabit.mailsender.service;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

public interface EmailSenderService {
    void generate() throws MessagingException;

    MimeMessage sendFile(List<Integer> deps, List<Float> orgTypes, String to) throws MessagingException;

}
