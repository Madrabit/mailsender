package ru.madrabit.mailsender.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
