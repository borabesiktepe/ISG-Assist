package com.borabesiktepe.isgassist.webApi.controllers;

import com.borabesiktepe.isgassist.business.abstracts.CompanyService;
import com.borabesiktepe.isgassist.business.requests.CreateCompanyRequest;
import com.borabesiktepe.isgassist.business.responses.CompaniesResponse;
import com.borabesiktepe.isgassist.dataAccess.WorkplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompaniesController {
	private CompanyService companyService;

	@Autowired
	private WorkplaceRepository workplaceRepository;

	@Autowired
	public CompaniesController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping("/getall")
	public List<CompaniesResponse> getAllByWorkplaceId(@RequestParam int workplaceId) {
		return companyService.getAllByWorkplaceId(workplaceId);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateCompanyRequest createCompanyRequest) {
		this.companyService.add(createCompanyRequest);
	}

	@PutMapping("/update/{workplaceId}")
	public void update(@PathVariable int workplaceId, @RequestBody CreateCompanyRequest createCompanyRequest) {
		this.companyService.update(workplaceId, createCompanyRequest);
	}
}