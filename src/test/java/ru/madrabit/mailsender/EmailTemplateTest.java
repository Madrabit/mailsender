package ru.madrabit.mailsender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTemplateTest {

    @Test
    public void readingHtml() {
        EmailTemplate template = new EmailTemplate();
        final String s = template.readFile();
        System.out.println(s);
    }
}
