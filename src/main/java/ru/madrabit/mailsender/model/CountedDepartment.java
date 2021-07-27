package ru.madrabit.mailsender.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
@IdClass(IdCountedDep.class)
public class CountedDepartment implements Serializable {

    @Id
    private Integer depNumber;

    @Id
    private Float depType;

    private Long amount;
    private String depName;

    public CountedDepartment(Long amount, Integer depNumber, String depName, Float depType) {
        this.depNumber = depNumber;
        this.depType = depType;
        this.amount = amount;
        this.depName = depName;
    }
}
