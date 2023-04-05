package com.borabesiktepe.isgassist.dataAccess;

import com.borabesiktepe.isgassist.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
	List<Note> findAllByUserId(int userId);
}