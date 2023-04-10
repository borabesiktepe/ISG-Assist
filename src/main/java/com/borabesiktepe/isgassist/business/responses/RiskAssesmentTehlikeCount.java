package com.borabesiktepe.isgassist.business.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RiskAssesmentTehlikeCount {
    private String tehlikeAdi;
    private int olaySayisi;
}