package com.loginregistration.authtest.webApi.controllers;

import com.loginregistration.authtest.business.abstracts.CompanyService;
import com.loginregistration.authtest.business.requests.CreateCompanyRequest;
import com.loginregistration.authtest.business.responses.CompaniesResponse;
import com.loginregistration.authtest.dataAccess.WorkplaceRepository;
import com.loginregistration.authtest.entities.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
}