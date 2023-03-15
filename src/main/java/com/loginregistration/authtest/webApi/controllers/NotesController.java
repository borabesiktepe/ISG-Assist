package com.loginregistration.authtest.webApi.controllers;

import com.loginregistration.authtest.business.abstracts.NoteService;
import com.loginregistration.authtest.business.abstracts.WorkplaceService;
import com.loginregistration.authtest.business.requests.CreateNoteRequest;
import com.loginregistration.authtest.business.requests.CreateWorkplaceRequest;
import com.loginregistration.authtest.business.responses.NotesResponse;
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
@RequestMapping("/api/notes")
public class NotesController {
	private NoteService noteService;

	@Autowired
	public NotesController(NoteService noteService) {
		this.noteService = noteService;
	}
	
	@GetMapping("/getnotes")
	public List<NotesResponse> getAll() {
		Optional<User> userOptional = SecurityUtils.getUser();

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return noteService.getAllByUserId(user.getId());
			//return workplaceService.getAllByUserId(user.getId());
		}

		return new ArrayList<>();
	}
	
	@PostMapping("/add")
	public void add(CreateNoteRequest createNoteRequest) {
		this.noteService.add(createNoteRequest);
	}
}
