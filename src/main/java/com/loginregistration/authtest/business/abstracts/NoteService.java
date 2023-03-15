package com.loginregistration.authtest.business.abstracts;

import com.loginregistration.authtest.business.requests.CreateNoteRequest;
import com.loginregistration.authtest.business.responses.NotesResponse;

import java.util.List;

public interface NoteService {
	List<NotesResponse> getAll();
	void add(CreateNoteRequest createNoteRequest);

	List<NotesResponse> getAllByUserId(int user);
}