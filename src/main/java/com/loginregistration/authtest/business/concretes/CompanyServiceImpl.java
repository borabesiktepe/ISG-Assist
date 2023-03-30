package com.loginregistration.authtest.business.concretes;

import com.loginregistration.authtest.business.abstracts.CompanyService;
import com.loginregistration.authtest.business.requests.CreateCompanyRequest;
import com.loginregistration.authtest.business.responses.CompaniesResponse;
import com.loginregistration.authtest.dataAccess.CompanyRepository;
import com.loginregistration.authtest.dataAccess.WorkplaceRepository;
import com.loginregistration.authtest.entities.Company;
import com.loginregistration.authtest.entities.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private WorkplaceRepository workplaceRepository;
	
	@Override
	public List<CompaniesResponse> getAll() {
		List<Company> companies = companyRepository.findAll();
		List<CompaniesResponse> companiesResponses = new ArrayList<CompaniesResponse>();
		
		for (Company company : companies) {
			CompaniesResponse responseItem = new CompaniesResponse();

			responseItem.setId(company.getId());
			responseItem.setAddress(company.getAddress());
			responseItem.setCity(company.getCity());
			responseItem.setMail(company.getMail());
			responseItem.setPhone(company.getPhone());
			responseItem.setContactPerson(company.getContactPerson());
			responseItem.setContactPersonPhone(company.getContactPersonPhone());
			responseItem.setWorkplaceId(company.getWorkplace().getId());

			companiesResponses.add(responseItem);
		}
		
		return companiesResponses;
	}

	@Override
	public void add(CreateCompanyRequest createCompanyRequest) {
		Workplace workplace = workplaceRepository.findById(createCompanyRequest.getWorkplaceId()).orElseThrow(() -> new RuntimeException("Workplace ID bulunamadı."));

		Company company = new Company();

		company.setAddress(createCompanyRequest.getAddress());
		company.setCity(createCompanyRequest.getCity());
		company.setMail(createCompanyRequest.getMail());
		company.setPhone(createCompanyRequest.getPhone());
		company.setContactPerson(createCompanyRequest.getContactPerson());
		company.setContactPersonPhone(createCompanyRequest.getContactPersonPhone());
		company.setWorkplace(workplace);

		this.companyRepository.save(company);
	}

	@Override
	public List<CompaniesResponse> getAllByWorkplaceId(int workplaceId) {
		return this.companyRepository.findAllByWorkplaceId(workplaceId).stream()
				.map(company -> new CompaniesResponse(company.getId(), company.getAddress(), company.getCity(), company.getMail(),
						company.getPhone(), company.getContactPerson(), company.getContactPersonPhone(), company.getWorkplace().getId()))
				.collect(Collectors.toList());
	}

	@Override
	public void update(Integer workplaceId, CreateCompanyRequest createCompanyRequest) {
		Company updateCompany = companyRepository.findByWorkplaceId(workplaceId)
				.orElseThrow(() -> new RuntimeException("Company bulunmadı: " + workplaceId));

		updateCompany.setAddress(createCompanyRequest.getAddress());
		updateCompany.setCity(createCompanyRequest.getCity());
		updateCompany.setMail(createCompanyRequest.getMail());
		updateCompany.setPhone(createCompanyRequest.getPhone());
		updateCompany.setContactPerson(createCompanyRequest.getContactPerson());
		updateCompany.setContactPersonPhone(createCompanyRequest.getContactPersonPhone());

		companyRepository.save(updateCompany);
	}
}