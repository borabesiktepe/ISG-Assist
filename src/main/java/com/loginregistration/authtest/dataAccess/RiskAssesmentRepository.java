package com.loginregistration.authtest.dataAccess;

import com.loginregistration.authtest.entities.RiskAssesment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiskAssesmentRepository extends JpaRepository<RiskAssesment, Integer> {
    List<RiskAssesment> findAllByWorkplaceId(int workplaceId);
    Optional<RiskAssesment> findByWorkplaceId(int workplaceId);
}
