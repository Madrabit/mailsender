package ru.madrabit.mailsender.model;

import javax.persistence.*;

@Entity
@Table(name = "dbo.Attr5")
public class Сounterparty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer objectId;

    // something important for query
    @Column(name = "p3284")
    private Integer p3284;
    @Column(name = "p2385")
    private Integer revokedLicense;
    @Column(name = "p2386")
    private Integer bankLiquidated;

}
