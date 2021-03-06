package ru.madrabit.mailsender.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Integer objectId;
    private String name;
    private String patronymic;
    private String email;
    private String depName;
    private Integer departmentNumber;
}
