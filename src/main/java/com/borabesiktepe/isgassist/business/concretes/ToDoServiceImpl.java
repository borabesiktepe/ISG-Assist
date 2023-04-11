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

	@Autowired
	private ToDoRepository toDoRepository;

	@Override
	public List<ToDosResponse> getAllByUserId(int userId) {
		return this.toDoRepository.findAllByUserId(userId).stream()
				.map(toDo -> new ToDosResponse(toDo.getId(), toDo.getTodoItem(), toDo.getUser().getId()))
				.collect(Collectors.toList());
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
	public void clear(int userId) {
		toDoRepository.findAllByUserId(userId).forEach(toDo -> toDoRepository.delete(toDo));
	}
}
