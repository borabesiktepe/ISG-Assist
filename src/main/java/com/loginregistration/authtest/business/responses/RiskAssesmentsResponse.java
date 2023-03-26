package com.loginregistration.authtest.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskAssesmentsResponse {
    private int id;
    private String tehlikeAdi;
    private String yerEkipman;
    private String mevcutTehlikeler;
    private String olusacakRiskler;
    private String mevcutOnlemler;
    private String maruzKalanlar;
    private int siddet;
    private int olasilik;
    private int risk;
    private String alinacakTedbirler;
    private int sonSiddet;
    private int sonOlasilik;
    private int sonRisk;
    private int workplaceId;
}