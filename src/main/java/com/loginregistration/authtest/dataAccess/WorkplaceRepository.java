package com.loginregistration.authtest.dataAccess;

import com.loginregistration.authtest.entities.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Integer> {
	List<Workplace> findAllByUserId(int userId);
	Optional<Workplace> findById(int workplaceId);
}