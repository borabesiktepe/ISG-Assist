package com.borabesiktepe.isgassist.business.concretes;

import com.borabesiktepe.isgassist.business.abstracts.CompanyService;
import com.borabesiktepe.isgassist.business.requests.CreateCompanyRequest;
import com.borabesiktepe.isgassist.business.responses.CompaniesResponse;
import com.borabesiktepe.isgassist.dataAccess.CompanyRepository;
import com.borabesiktepe.isgassist.dataAccess.WorkplaceRepository;
import com.borabesiktepe.isgassist.entities.Company;
import com.borabesiktepe.isgassist.entities.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<CompaniesResponse> getAllByWorkplaceId(int workplaceId) {
		return this.companyRepository.findAllByWorkplaceId(workplaceId).stream()
				.map(company -> new CompaniesResponse(company.getId(), company.getAddress(), company.getCity(), company.getMail(),
						company.getPhone(), company.getContactPerson(), company.getContactPersonPhone(), company.getWorkplace().getId()))
				.collect(Collectors.toList());
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