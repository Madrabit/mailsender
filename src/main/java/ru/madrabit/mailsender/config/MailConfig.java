package ru.madrabit.mailsender.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configuration for mail server.
 * Properties saved in app...props.
 */
@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

    private String host;
    private String port;
    private String username;
    private String password;
    private String starttls;
    private String debug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));
        mailSender.setUsername(username);
        mailSender.setPassword(password);


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", debug);
        props.put("mail.smtp.allow8bitmime", "true");
        props.put("mail.smtps.allow8bitmime", "true");

        return mailSender;
    }

}
