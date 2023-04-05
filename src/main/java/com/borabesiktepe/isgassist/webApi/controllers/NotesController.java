package com.borabesiktepe.isgassist.webApi.controllers;

import com.borabesiktepe.isgassist.entities.User;
import com.borabesiktepe.isgassist.business.abstracts.NoteService;
import com.borabesiktepe.isgassist.business.requests.CreateNoteRequest;
import com.borabesiktepe.isgassist.business.responses.NotesResponse;
import com.borabesiktepe.isgassist.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	
	@GetMapping("/getall")
	public List<NotesResponse> getAll() {
		Optional<User> userOptional = SecurityUtils.getUser();

		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return noteService.getAllByUserId(user.getId());
		}

		return new ArrayList<>();
	}
	
	@PutMapping("/update")
	public void update(@RequestBody CreateNoteRequest createNoteRequest) {
		this.noteService.update(createNoteRequest);
	}
}
