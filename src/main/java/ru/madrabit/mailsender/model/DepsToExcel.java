package ru.madrabit.mailsender.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepsToExcel {
    private List<Integer> deps;
    private List<Float> orgTypes;
    private String email;
}
