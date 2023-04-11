package com.borabesiktepe.isgassist.business.concretes;

import com.borabesiktepe.isgassist.dataAccess.NoteRepository;
import com.borabesiktepe.isgassist.entities.Note;
import com.borabesiktepe.isgassist.security.SecurityUtils;
import com.borabesiktepe.isgassist.business.abstracts.NoteService;
import com.borabesiktepe.isgassist.business.requests.CreateNoteRequest;
import com.borabesiktepe.isgassist.business.responses.NotesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;

	@Override
	public List<NotesResponse> getAllByUserId(int userId) {
		return this.noteRepository.findAllByUserId(userId).stream()
				.map(note -> new NotesResponse(note.getId(), note.getNote(), note.getUser().getId()))
				.collect(Collectors.toList());
	}

	@Override
	public void update(CreateNoteRequest createNoteRequest) {
		Note note = new Note();

		if (SecurityUtils.getUser().isPresent()) {
			note.setNote(createNoteRequest.getNote());
			note.setUser(SecurityUtils.getUser().get());
		}
		
		this.noteRepository.save(note);
	}
}
