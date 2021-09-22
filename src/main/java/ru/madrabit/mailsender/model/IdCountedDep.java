package ru.madrabit.mailsender.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class IdCountedDep implements Serializable {

    private Integer depNumber;

    private Float depType;
}
