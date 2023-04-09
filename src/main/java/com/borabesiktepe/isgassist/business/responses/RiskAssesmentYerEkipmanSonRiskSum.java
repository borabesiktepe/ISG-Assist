package com.borabesiktepe.isgassist.business.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RiskAssesmentYerEkipmanSonRiskSum {
    private String yerEkipman;
    private int toplamRisk;
}
