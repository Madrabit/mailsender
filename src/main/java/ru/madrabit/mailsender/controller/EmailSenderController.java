package ru.madrabit.mailsender.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import ru.madrabit.mailsender.model.DepsToExcel;
import ru.madrabit.mailsender.reader.HtmlReader;
import ru.madrabit.mailsender.service.impl.EmailSenderServiceImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailSenderController {

    private final JavaMailSender emailSender;
    private final EmailSenderServiceImpl emailSenderServiceImpl;
    private final RedisTemplate redisTemplate;
    private final HtmlReader htmlReader;


    @ApiOperation(value = "Send emails")
    @GetMapping("/send/{subject}")
    public void sendSimpleMessage(@PathVariable String subject) throws MessagingException {
        emailSenderServiceImpl.setSubject(subject);
        emailSenderServiceImpl.generate();
        List<MimeMessage> messages = emailSenderServiceImpl.getMessages();
        for (MimeMessage message : messages) {
            emailSender.send(message);
        }
        System.out.println("Finished");
    }

    @GetMapping("/check-html")
    public String sendSimpleMessage() {
        return htmlReader.readFile();
    }

    @PostMapping(value = "/query/fp/send/")
    public DepsToExcel sendEmailWithExcel(
            @RequestBody DepsToExcel depsToExcel)
            throws MessagingException {
        List<Integer> deps = depsToExcel.getDeps();
        List<Float> orgTypes = depsToExcel.getOrgTypes();
        String to = depsToExcel.getEmail();
        redisTemplate.opsForHash().put("deps", "deps", String.valueOf(deps));
        redisTemplate.opsForHash().put("orgTypes", "orgTypes", String.valueOf(orgTypes));
        MimeMessage message = emailSenderServiceImpl.sendFile(deps, orgTypes, to);
        if (message != null) {
            emailSender.send(message);
        }
        return depsToExcel;
    }
}
