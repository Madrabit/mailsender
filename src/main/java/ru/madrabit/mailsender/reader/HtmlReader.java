package ru.madrabit.mailsender.reader;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Getter
public class HtmlReader {
    private String template;


    public String readFile() {
        String result = "";
        try {
            result = Files.readString(Path.of("template.html"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setTemplate() {
        this.template = readFile();
    }
}
