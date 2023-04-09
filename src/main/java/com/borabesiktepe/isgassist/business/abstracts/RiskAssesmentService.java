package com.borabesiktepe.isgassist.business.abstracts;

import com.borabesiktepe.isgassist.business.requests.CreateRiskAssesmentRequest;
import com.borabesiktepe.isgassist.business.responses.RiskAssesmentTehlikeCount;
import com.borabesiktepe.isgassist.business.responses.RiskAssesmentYerEkipmanCount;
import com.borabesiktepe.isgassist.business.responses.RiskAssesmentsResponse;

import java.util.List;

public interface RiskAssesmentService {
    List<RiskAssesmentsResponse> getAll();
    void add(CreateRiskAssesmentRequest createRiskAssesmentRequest);
    List<RiskAssesmentsResponse> getAllByWorkplaceId(int workplaceId);
    void update(Integer id, CreateRiskAssesmentRequest createRiskAssesmentRequest);
    void delete(int id);
    List<RiskAssesmentTehlikeCount> enTekrarEdenTehlikeler(int workplaceId);
    List<RiskAssesmentYerEkipmanCount> enTehlikeliYerEkipmanlar(int workplaceId);
}
