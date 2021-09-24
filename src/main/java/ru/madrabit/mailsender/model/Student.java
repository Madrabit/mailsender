package ru.madrabit.mailsender.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Getter
@Setter
public class Student {

    private final Integer studentId;
    private final String studentName;
}
