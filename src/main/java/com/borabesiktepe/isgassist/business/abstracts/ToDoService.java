package com.borabesiktepe.isgassist.business.abstracts;

import com.borabesiktepe.isgassist.business.requests.CreateToDoRequest;
import com.borabesiktepe.isgassist.business.responses.ToDosResponse;

import java.util.List;

public interface ToDoService {
	List<ToDosResponse> getAll();
	void add(CreateToDoRequest createToDoRequest);
	List<ToDosResponse> getAllByUserId(int userId);
	void clear(int userId);
}