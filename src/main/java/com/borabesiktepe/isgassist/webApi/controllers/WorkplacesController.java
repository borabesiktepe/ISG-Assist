package com.borabesiktepe.isgassist.webApi.controllers;

import com.borabesiktepe.isgassist.business.abstracts.WorkplaceService;
import com.borabesiktepe.isgassist.business.requests.CreateWorkplaceRequest;
import com.borabesiktepe.isgassist.business.responses.WorkplacesResponse;
import com.borabesiktepe.isgassist.entities.User;
import com.borabesiktepe.isgassist.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public void add(@RequestBody CreateWorkplaceRequest createWorkplaceRequest) {
		this.workplaceService.add(createWorkplaceRequest);
	}

	@PutMapping("/update/{id}")
	public void update(@PathVariable int id, @RequestBody CreateWorkplaceRequest createWorkplaceRequest) {
		this.workplaceService.update(id, createWorkplaceRequest);
	}
}