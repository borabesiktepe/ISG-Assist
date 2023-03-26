package com.loginregistration.authtest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "risk_assesments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RiskAssesment {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String tehlikeAdi;

    @Column
    private String yerEkipman;

    @Column
    private String mevcutTehlikeler;

    @Column
    private String olusacakRiskler;

    @Column
    private String mevcutOnlemler;

    @Column
    private String maruzKalanlar;

    @Column
    private int siddet;

    @Column
    private int olasilik;

    @Column
    private int risk;

    @Column
    private String alinacakTedbirler;

    @Column
    private int sonSiddet;

    @Column
    private int sonOlasilik;

    @Column
    private int sonRisk;

    @ManyToOne
    private Workplace workplace;
}
