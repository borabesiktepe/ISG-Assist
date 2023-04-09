package com.borabesiktepe.isgassist.business.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RiskAssesmentSummaryCount {
    private String tehlikeAdi;
    private int count;
}
