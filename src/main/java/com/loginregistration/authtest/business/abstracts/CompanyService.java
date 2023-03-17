package com.loginregistration.authtest.business.abstracts;

import com.loginregistration.authtest.business.requests.CreateCompanyRequest;
import com.loginregistration.authtest.business.responses.CompaniesResponse;

import java.util.List;

public interface CompanyService {
	List<CompaniesResponse> getAll();
	void add(CreateCompanyRequest createWorkplaceRequest);

	List<CompaniesResponse> getAllByWorkplaceId(int workplaceId);
}