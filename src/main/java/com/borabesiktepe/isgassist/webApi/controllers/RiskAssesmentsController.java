package com.borabesiktepe.isgassist.webApi.controllers;

import com.borabesiktepe.isgassist.business.abstracts.RiskAssesmentService;
import com.borabesiktepe.isgassist.business.requests.CreateRiskAssesmentRequest;
import com.borabesiktepe.isgassist.business.responses.*;
import com.borabesiktepe.isgassist.dataAccess.RiskAssesmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riskassesments")
public class RiskAssesmentsController {
    private RiskAssesmentService riskAssesmentService;

    @Autowired
    private RiskAssesmentRepository riskAssesmentRepository;

    @Autowired
    public RiskAssesmentsController(RiskAssesmentService riskAssesmentService) {
        this.riskAssesmentService = riskAssesmentService;
    }

    @GetMapping("/getall")
    public List<RiskAssesmentsResponse> getAllByWorkplaceId(@RequestParam int workplaceId) {
        return riskAssesmentService.getAllByWorkplaceId(workplaceId);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateRiskAssesmentRequest createRiskAssesmentRequest) {
        this.riskAssesmentService.add(createRiskAssesmentRequest);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable int id, @RequestBody CreateRiskAssesmentRequest createRiskAssesmentRequest) {
        this.riskAssesmentService.update(id, createRiskAssesmentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        this.riskAssesmentService.delete(id);
    }

    @GetMapping("/getRiskTehlikeCount")
    public List<RiskAssesmentTehlikeCount> getRiskTehlikeCountList(@RequestParam int workplaceId) {
        return riskAssesmentService.enTekrarEdenTehlikeler(workplaceId);
    }

    @GetMapping("/getRiskYerEkipmanCount")
    public List<RiskAssesmentYerEkipmanCount> getRiskYerEkipmanCountList(@RequestParam int workplaceId) {
        return riskAssesmentService.enTehlikeliYerEkipmanlar(workplaceId);
    }

    @GetMapping("/getTehlikeAdiRiskSum")
    public List<RiskAssesmentTehlikeRiskSum> getTehlikeAdiRiskSumList(@RequestParam int workplaceId) {
        return riskAssesmentService.tehlikeAdiToplamRisk(workplaceId);
    }

    @GetMapping("/getTehlikeAdiSonRiskSum")
    public List<RiskAssesmentTehlikeSonRiskSum> getTehlikeAdiSonRiskSumList(@RequestParam int workplaceId) {
        return riskAssesmentService.tehlikeAdiToplamSonRisk(workplaceId);
    }

    @GetMapping("/getYerEkipmanRiskSum")
    public List<RiskAssesmentYerEkipmanRiskSum> getYerEkipmanRiskSumList(@RequestParam int workplaceId) {
        return riskAssesmentService.yerEkipmanToplamRisk(workplaceId);
    }

    @GetMapping("/getYerEkipmanSonRiskSum")
    public List<RiskAssesmentYerEkipmanSonRiskSum> getYerEkipmanSonRiskSumList(@RequestParam int workplaceId) {
        return riskAssesmentService.yerEkipmanToplamSonRisk(workplaceId);
    }
}