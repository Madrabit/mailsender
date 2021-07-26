package ru.madrabit.mailsender.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dbo.attr166")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer objectId;

    @Column(name = "P1973")
    private String name;

    @Column(name = "P1974")
    private String surname;

    @Column(name = "P971")
    private String email;

    @Column(name = "p3251")
    private Integer p3251;

    @Column(name = "p3269")
    private Integer p3269;

    @Column(name = "p3270")
    private Integer unsubscribe;

    @Column(name = "p3271")
    private Integer p3271;

    @ManyToOne
            (optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "P2837")
    private Department department;


    @ManyToOne
            (optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "P944")
    private Counterparty counterparty;
}
