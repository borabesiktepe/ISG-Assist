package com.loginregistration.authtest.webApi.controllers;

import com.loginregistration.authtest.business.abstracts.CompanyService;
import com.loginregistration.authtest.business.requests.CreateCompanyRequest;
import com.loginregistration.authtest.business.responses.CompaniesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompaniesController {
	private CompanyService companyService;

	@Autowired
	public CompaniesController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping("/getall")
	public List<CompaniesResponse> getAllByWorkplaceId(@RequestParam int workplaceId) {
		return companyService.getAllByWorkplaceId(workplaceId);
	}
	
	@PostMapping("/add")
	public void add(CreateCompanyRequest createCompanyRequest) {
		this.companyService.add(createCompanyRequest);
	}
}