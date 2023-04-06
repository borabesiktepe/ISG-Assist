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

	private NoteRepository noteRepository;

	@Autowired
	public NoteServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}
	
	@Override
	public List<NotesResponse> getAll() {
		List<Note> notes = noteRepository.findAll();
		List<NotesResponse> notesResponses = new ArrayList<NotesResponse>();
		
		for (Note note : notes) {
			NotesResponse responseItem = new NotesResponse();

			responseItem.setId(note.getId());
			responseItem.setNote(note.getNote());
			responseItem.setUserId(note.getUser().getId());

			notesResponses.add(responseItem);
		}
		
		return notesResponses;
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

	@Override
	public List<NotesResponse> getAllByUserId(int userId) {
		return this.noteRepository.findAllByUserId(userId).stream()
				.map(note -> new NotesResponse(note.getId(), note.getNote(), note.getUser().getId()))
				.collect(Collectors.toList());
	}
}