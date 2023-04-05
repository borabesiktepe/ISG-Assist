package com.borabesiktepe.isgassist.business.concretes;

import com.borabesiktepe.isgassist.security.SecurityUtils;
import com.borabesiktepe.isgassist.business.abstracts.ToDoService;
import com.borabesiktepe.isgassist.business.requests.CreateToDoRequest;
import com.borabesiktepe.isgassist.business.responses.ToDosResponse;
import com.borabesiktepe.isgassist.dataAccess.ToDoRepository;
import com.borabesiktepe.isgassist.entities.ToDo;
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
