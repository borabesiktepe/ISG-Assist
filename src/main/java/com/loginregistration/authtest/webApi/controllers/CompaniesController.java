package com.loginregistration.authtest.webApi.controllers;

import com.loginregistration.authtest.business.abstracts.CompanyService;
import com.loginregistration.authtest.business.requests.CreateCompanyRequest;
import com.loginregistration.authtest.business.responses.CompaniesResponse;
import com.loginregistration.authtest.entities.Company;
import com.loginregistration.authtest.entities.Workplace;
import com.loginregistration.authtest.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompaniesController {
	private CompanyService companyService;

	@Autowired
	public CompaniesController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping("/getcompanies")
	public List<CompaniesResponse> getAll() {
		return companyService.getAll();
	}
	
	@PostMapping("/add")
	public void add(CreateCompanyRequest createCompanyRequest) {
		this.companyService.add(createCompanyRequest);
	}
}
