package ru.madrabit.mailsender;

import org.junit.jupiter.api.Test;
import ru.madrabit.mailsender.reader.HtmlReader;

class HtmlReaderTest {

    @Test
    public void readingHtml() {
        HtmlReader template = new HtmlReader();
        final String s = template.readFile();
        System.out.println(s);
    }
}
