package com.borabesiktepe.isgassist.dataAccess;

import com.borabesiktepe.isgassist.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
	List<ToDo> findAllByUserId(int userId);
}