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


//    @Query("SELECT r.tehlikeAdi, COUNT(*) as olaySayisi FROM RiskAssessment r WHERE r.workplace.id = :workplaceId GROUP BY r.tehlikeAdi ORDER BY olaySayisi DESC")
//    List<Object[]> findOlaySayisiByTehlikeAdiAndWorkplaceId(@Param("workplaceId") int workplaceId);
//
//    @Query("SELECT r.yerEkipman, COUNT(*) as olaySayisi FROM RiskAssessment r WHERE r.workplace.id = :workplaceId GROUP BY r.yerEkipman ORDER BY olaySayisi DESC")
//    List<Object[]> findOlaySayisiByYerEkipmanAndWorkplaceId(@Param("workplaceId") int workplaceId);
//
//    @Query("SELECT r.yerEkipman, SUM(r.risk) as toplamRisk FROM RiskAssessment r WHERE r.workplace.id = :workplaceId GROUP BY r.yerEkipman")
//    List<Object[]> findToplamRiskByYerEkipmanAndWorkplaceId(@Param("workplaceId") int workplaceId);
//
//    @Query("SELECT r.yerEkipman, SUM(r.sonRisk) as toplamRisk FROM RiskAssessment r WHERE r.workplace.id = :workplaceId GROUP BY r.yerEkipman")
//    List<Object[]> findToplamRiskByYerEkipmanAndWorkplaceId(@Param("workplaceId") Long workplaceId);
}