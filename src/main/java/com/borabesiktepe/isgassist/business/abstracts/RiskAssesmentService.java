package com.borabesiktepe.isgassist.business.abstracts;

import com.borabesiktepe.isgassist.business.requests.CreateRiskAssesmentRequest;
import com.borabesiktepe.isgassist.business.responses.*;

import java.util.List;

public interface RiskAssesmentService {
    List<RiskAssesmentsResponse> getAllByWorkplaceId(int workplaceId);
    void add(CreateRiskAssesmentRequest createRiskAssesmentRequest);
    void update(Integer id, CreateRiskAssesmentRequest createRiskAssesmentRequest);
    void delete(int id);
    List<RiskAssesmentTehlikeCount> enTekrarEdenTehlikeler(int workplaceId);
    List<RiskAssesmentYerEkipmanCount> enTehlikeliYerEkipmanlar(int workplaceId);
    List<RiskAssesmentTehlikeRiskSum> tehlikeAdiToplamRisk(int workplaceId);
    List<RiskAssesmentTehlikeSonRiskSum> tehlikeAdiToplamSonRisk(int workplaceId);
    List<RiskAssesmentYerEkipmanRiskSum> yerEkipmanToplamRisk(int workplaceId);
    List<RiskAssesmentYerEkipmanSonRiskSum> yerEkipmanToplamSonRisk(int workplaceId);
}
