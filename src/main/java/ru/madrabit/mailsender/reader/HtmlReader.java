package ru.madrabit.mailsender.reader;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.madrabit.mailsender.config.StorageProperties;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Getter
public class HtmlReader implements TemplateReader {
    private String template;
    private final Path rootLocation;

    public HtmlReader(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String readFile() {
        try {
            this.template = Files.readString(Path.of(rootLocation + File.separator + "template.html"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.template;
    }
}
