package com.loginregistration.authtest.dataAccess;

import com.loginregistration.authtest.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
	List<Note> findAllByUserId(int userId);
}