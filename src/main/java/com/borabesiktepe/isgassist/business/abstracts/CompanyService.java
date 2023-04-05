package com.borabesiktepe.isgassist.business.abstracts;

import com.borabesiktepe.isgassist.business.responses.CompaniesResponse;
import com.borabesiktepe.isgassist.business.requests.CreateCompanyRequest;

import java.util.List;

public interface CompanyService {
	List<CompaniesResponse> getAll();
	void add(CreateCompanyRequest createWorkplaceRequest);
	List<CompaniesResponse> getAllByWorkplaceId(int workplaceId);
    void update(Integer id, CreateCompanyRequest createCompanyRequest);
}