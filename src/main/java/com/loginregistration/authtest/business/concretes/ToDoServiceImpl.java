package com.loginregistration.authtest.business.concretes;

import com.loginregistration.authtest.business.abstracts.ToDoService;
import com.loginregistration.authtest.business.requests.CreateToDoRequest;
import com.loginregistration.authtest.business.responses.ToDosResponse;
import com.loginregistration.authtest.dataAccess.ToDoRepository;
import com.loginregistration.authtest.entities.ToDo;
import com.loginregistration.authtest.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

	private ToDoRepository toDoRepository;

	@Autowired
	public ToDoServiceImpl(ToDoRepository toDoRepository) {
		this.toDoRepository = toDoRepository;
	}
	
	@Override
	public List<ToDosResponse> getAll() {
		List<ToDo> todos = toDoRepository.findAll();
		List<ToDosResponse> toDosResponse = new ArrayList<ToDosResponse>();
		
		for (ToDo todo : todos) {
			ToDosResponse responseItem = new ToDosResponse();

			responseItem.setId(todo.getId());
			responseItem.setTodoItem(todo.getTodoItem());
			responseItem.setUserId(todo.getUser().getId());

			toDosResponse.add(responseItem);
		}
		
		return toDosResponse;
	}

	@Override
	public void add(CreateToDoRequest createToDoRequest) {
		ToDo todo = new ToDo();

		if (SecurityUtils.getUser().isPresent()) {
			todo.setTodoItem(createToDoRequest.getTodoItem());
			todo.setUser(SecurityUtils.getUser().get());
		}

		this.toDoRepository.save(todo);
	}

	@Override
	public List<ToDosResponse> getAllByUserId(int userId) {
		return this.toDoRepository.findAllByUserId(userId).stream()
				.map(toDo -> new ToDosResponse(toDo.getId(), toDo.getTodoItem(), toDo.getUser().getId()))
				.collect(Collectors.toList());
	}

	@Override
	public void clear(int userId) {
		toDoRepository.findAllByUserId(userId).forEach(toDo -> toDoRepository.delete(toDo));
	}
}
