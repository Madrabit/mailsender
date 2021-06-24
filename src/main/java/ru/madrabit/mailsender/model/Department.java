package ru.madrabit.mailsender.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dbo.Attr369")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer objectId;

    @Column(name = "P2835")
    String depName;

    @Column(name = "P2838")
    Integer departmentNumber;

}
