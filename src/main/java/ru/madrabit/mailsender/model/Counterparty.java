package ru.madrabit.mailsender.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Contragent
 */
@Entity
@Getter
@Setter
@Table(name = "dbo.Attr5")
public class Counterparty {

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
    @Column(name = "p3177")
    private Boolean hasDepartment;

    // NFO, Bank, bank branch
    @Column(name = "p3189")
    private Float ctpType;

}
