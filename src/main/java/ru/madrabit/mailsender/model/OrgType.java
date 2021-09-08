package ru.madrabit.mailsender.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class OrgType {
    private String name;
    private float number;
    private boolean checked;
}
