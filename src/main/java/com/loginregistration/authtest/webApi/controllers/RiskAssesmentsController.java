package com.loginregistration.authtest.webApi.controllers;

import com.loginregistration.authtest.business.abstracts.RiskAssesmentService;
import com.loginregistration.authtest.business.responses.RiskAssesmentsResponse;
import com.loginregistration.authtest.dataAccess.RiskAssesmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
