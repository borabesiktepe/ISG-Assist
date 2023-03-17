package com.loginregistration.authtest.webApi.controllers;

import com.loginregistration.authtest.business.abstracts.WorkplaceService;
import com.loginregistration.authtest.business.requests.CreateWorkplaceRequest;
import com.loginregistration.authtest.business.responses.WorkplacesResponse;
import com.loginregistration.authtest.entities.User;
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
@RequestMapping("/api/workplaces")
public class WorkplacesController {
	private WorkplaceService workplaceService;

	@Autowired
	public WorkplacesController(WorkplaceService workplaceService) {
		this.workplaceService = workplaceService;
	}
	
	@GetMapping("/getall")
	public List<WorkplacesResponse> getAll() {
		Optional<User> userOptional = SecurityUtils.getUser();

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return workplaceService.getAllByUserId(user.getId());
		}

		return new ArrayList<>();
	}
	
	@PostMapping("/add")
	public void add(CreateWorkplaceRequest createWorkplaceRequest) {
		this.workplaceService.add(createWorkplaceRequest);
	}
}
