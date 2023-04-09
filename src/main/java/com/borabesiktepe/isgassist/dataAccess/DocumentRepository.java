package com.borabesiktepe.isgassist.dataAccess;

import com.borabesiktepe.isgassist.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
	List<Document> findAllByWorkplaceId(int workplaceId);
}