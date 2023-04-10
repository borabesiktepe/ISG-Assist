package com.borabesiktepe.isgassist.dataAccess;

import com.borabesiktepe.isgassist.entities.RiskAssesment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiskAssesmentRepository extends JpaRepository<RiskAssesment, Integer> {
    List<RiskAssesment> findAllByWorkplaceId(int workplaceId);
    Optional<RiskAssesment> findById(int id);
}