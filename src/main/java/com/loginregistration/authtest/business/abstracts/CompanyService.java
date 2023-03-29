package com.loginregistration.authtest.business.abstracts;

import com.loginregistration.authtest.business.responses.CompaniesResponse;
import com.loginregistration.authtest.business.requests.CreateCompanyRequest;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyService {
	List<CompaniesResponse> getAll();
	void add(CreateCompanyRequest createWorkplaceRequest);
	List<CompaniesResponse> getAllByWorkplaceId(int workplaceId);
    void update(Integer id, CreateCompanyRequest createCompanyRequest);
}