package ru.madrabit.mailsender.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import ru.madrabit.mailsender.exception.InvalidInputException;
import ru.madrabit.mailsender.model.DepsToExcel;
import ru.madrabit.mailsender.reader.HtmlReader;
import ru.madrabit.mailsender.service.EmailSenderService;
import ru.madrabit.mailsender.service.fp.QueryService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@RestController
@Api
@RequiredArgsConstructor
@Slf4j
public class EmailSenderController {

    private final JavaMailSender emailSender;
    private final EmailSenderService emailSenderService;
    private final RedisTemplate redisTemplate;
    private final QueryService service;
    private HtmlReader htmlReader;


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
        MimeMessage message = emailSenderService.sendFile(deps, orgTypes, to);
        if (message != null) {
            emailSender.send(message);
        }
        return depsToExcel;
    }
}
