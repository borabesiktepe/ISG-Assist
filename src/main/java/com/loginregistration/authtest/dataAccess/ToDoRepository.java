package com.loginregistration.authtest.dataAccess;

import com.loginregistration.authtest.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
	List<ToDo> findAllByUserId(int userId);
}