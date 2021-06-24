package ru.madrabit.mailsender.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.madrabit.mailsender.model.Department;

@Getter
@Setter
@Builder
public class EmployeeDTO {
    private Integer objectId;
    private String name;
    private String sureName;
    private String email;
    private String department;
    private Integer departmentNum;
}
