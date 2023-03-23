package com.loginregistration.authtest.business.concretes;

import com.loginregistration.authtest.business.abstracts.CompanyService;
import com.loginregistration.authtest.business.requests.CreateCompanyRequest;
import com.loginregistration.authtest.business.responses.CompaniesResponse;
import com.loginregistration.authtest.business.responses.NotesResponse;
import com.loginregistration.authtest.dataAccess.CompanyRepository;
import com.loginregistration.authtest.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;

	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	@Override
	public List<CompaniesResponse> getAll() {
		List<Company> companies = companyRepository.findAll();
		List<CompaniesResponse> workplacesResponses = new ArrayList<CompaniesResponse>();
		
		for (Company company : companies) {
			CompaniesResponse responseItem = new CompaniesResponse();

			responseItem.setId(company.getId());
			responseItem.setAddress(company.getAddress());
			responseItem.setMail(company.getMail());
			responseItem.setPhone(company.getPhone());
			responseItem.setContactPerson(company.getContactPerson());
			responseItem.setWorkplaceId(company.getWorkplace().getId());

			workplacesResponses.add(responseItem);
		}
		
		return workplacesResponses;
	}

	@Override
	public void add(CreateCompanyRequest createCompanyRequest) {
		Company company = new Company();

		company.setAddress(createCompanyRequest.getAddress());
		company.setMail(createCompanyRequest.getMail());
		company.setPhone(createCompanyRequest.getPhone());
		company.setContactPerson(createCompanyRequest.getContactPerson());
		//company.setWorkplace(createCompanyRequest.getWorkplaceId());
		
		this.companyRepository.save(company);
	}

	@Override
	public List<CompaniesResponse> getAllByWorkplaceId(int workplaceId) {
		return this.companyRepository.findAllByWorkplaceId(workplaceId).stream()
				.map(company -> new CompaniesResponse(company.getId(), company.getAddress(), company.getMail(), company.getPhone(), company.getContactPerson(), company.getWorkplace().getId()))
				.collect(Collectors.toList());
	}
}
