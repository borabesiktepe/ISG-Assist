package com.borabesiktepe.isgassist.webApi.controllers;

import com.borabesiktepe.isgassist.entities.User;
import com.borabesiktepe.isgassist.security.SecurityUtils;
import com.borabesiktepe.isgassist.business.abstracts.ToDoService;
import com.borabesiktepe.isgassist.business.requests.CreateToDoRequest;
import com.borabesiktepe.isgassist.business.responses.ToDosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public void add(@RequestBody CreateToDoRequest createToDoRequest) {
		this.toDoService.add(createToDoRequest);
	}

	@DeleteMapping("/delete")
	public void clear() {
		this.toDoService.clear(SecurityUtils.getUser().get().getId());
	}
}