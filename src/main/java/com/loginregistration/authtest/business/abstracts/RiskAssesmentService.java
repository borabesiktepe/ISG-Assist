package com.loginregistration.authtest.business.abstracts;

import com.loginregistration.authtest.business.requests.CreateRiskAssesmentRequest;
import com.loginregistration.authtest.business.responses.RiskAssesmentsResponse;

import java.util.List;

public interface RiskAssesmentService {
    List<RiskAssesmentsResponse> getAll();
    void add(CreateRiskAssesmentRequest createRiskAssesmentRequest);
    List<RiskAssesmentsResponse> getAllByWorkplaceId(int workplaceId);
}
