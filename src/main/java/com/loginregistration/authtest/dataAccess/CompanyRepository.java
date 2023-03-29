package com.loginregistration.authtest.dataAccess;

import com.loginregistration.authtest.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	List<Company> findAllByWorkplaceId(int workplaceId);
	Optional<Company> findByWorkplaceId(int workplaceId);
}