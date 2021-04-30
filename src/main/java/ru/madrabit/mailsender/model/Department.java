package ru.madrabit.mailsender.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dbo.Attr369")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer objectId;

    @Column(name = "P2835")
    String name;

    @Column(name = "P2838")
    Integer departmentNumber;

}
