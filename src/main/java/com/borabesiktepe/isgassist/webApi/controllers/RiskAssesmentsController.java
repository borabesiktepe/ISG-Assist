package com.borabesiktepe.isgassist.webApi.controllers;

import com.borabesiktepe.isgassist.business.abstracts.RiskAssesmentService;
import com.borabesiktepe.isgassist.business.requests.CreateRiskAssesmentRequest;
import com.borabesiktepe.isgassist.business.responses.RiskAssesmentSummaryCount;
import com.borabesiktepe.isgassist.business.responses.RiskAssesmentsResponse;
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

    @GetMapping("/getRiskSummaryCount")
    public List<RiskAssesmentSummaryCount> getRiskSummaryCountList(@RequestParam int workplaceId) {
        return riskAssesmentService.enTekrarEdenTehlikeler(workplaceId);
    }
}