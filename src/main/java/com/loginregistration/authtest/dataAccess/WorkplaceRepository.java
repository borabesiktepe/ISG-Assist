package com.loginregistration.authtest.dataAccess;

import com.loginregistration.authtest.entities.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Integer> {
	List<Workplace> findAllByUserId(int userId);
}