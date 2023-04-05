package com.borabesiktepe.isgassist.business.abstracts;

import com.borabesiktepe.isgassist.business.requests.CreateNoteRequest;
import com.borabesiktepe.isgassist.business.responses.NotesResponse;

import java.util.List;

public interface NoteService {
	List<NotesResponse> getAll();
	void update(CreateNoteRequest createNoteRequest);
	List<NotesResponse> getAllByUserId(int user);
}