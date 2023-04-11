package com.borabesiktepe.isgassist.business.abstracts;

import com.borabesiktepe.isgassist.business.responses.CompaniesResponse;
import com.borabesiktepe.isgassist.business.requests.CreateCompanyRequest;

import java.util.List;

public interface CompanyService {
	List<CompaniesResponse> getAllByWorkplaceId(int workplaceId);
	void add(CreateCompanyRequest createWorkplaceRequest);
    void update(Integer id, CreateCompanyRequest createCompanyRequest);
}