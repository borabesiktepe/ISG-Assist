package com.loginregistration.authtest.webApi.controllers;

import com.loginregistration.authtest.business.abstracts.ToDoService;
import com.loginregistration.authtest.business.requests.CreateToDoRequest;
import com.loginregistration.authtest.business.responses.ToDosResponse;
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
@RequestMapping("/api/todos")
public class ToDosController {
	private ToDoService toDoService;

	@Autowired
	public ToDosController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}
	
	@GetMapping("/getall")
	public List<ToDosResponse> getAll() {
		Optional<User> userOptional = SecurityUtils.getUser();

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return toDoService.getAllByUserId(user.getId());
		}

		return new ArrayList<>();
	}
	
	@PostMapping("/add")
	public void add(CreateToDoRequest createToDoRequest) {
		this.toDoService.add(createToDoRequest);
	}
}
