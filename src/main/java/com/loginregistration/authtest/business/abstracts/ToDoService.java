package com.loginregistration.authtest.business.abstracts;

import com.loginregistration.authtest.business.requests.CreateToDoRequest;
import com.loginregistration.authtest.business.responses.ToDosResponse;

import java.util.List;

public interface ToDoService {
	List<ToDosResponse> getAll();
	void add(CreateToDoRequest createToDoRequest);
	List<ToDosResponse> getAllByUserId(int userId);
	void clear(int userId);
}