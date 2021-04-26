package ru.madrabit.mailsender.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dbo.Attr336")
@Getter
@Setter
@NoArgsConstructor
public class SeminarStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer objectId;

    @Column(name = "P2537")
    private String status;


}
