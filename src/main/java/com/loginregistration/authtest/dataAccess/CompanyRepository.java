package com.loginregistration.authtest.dataAccess;

import com.loginregistration.authtest.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	List<Company> findAllByWorkplaceId(int workplaceId);
}