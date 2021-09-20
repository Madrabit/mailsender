package ru.madrabit.mailsender.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;


@AllArgsConstructor
@Data
@Getter
@Setter
public class DepartmentToFront {

    String depName;

    Integer depNumber;

    private Long amountSng;

    private Long amountBanks;

    private Long amountNfo;

    private Long amountBanksBranches;

    private boolean checkbox;

}
