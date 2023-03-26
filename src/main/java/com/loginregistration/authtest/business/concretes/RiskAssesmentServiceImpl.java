package com.loginregistration.authtest.business.concretes;

import com.loginregistration.authtest.business.abstracts.RiskAssesmentService;
import com.loginregistration.authtest.business.requests.CreateRiskAssesmentRequest;
import com.loginregistration.authtest.business.responses.RiskAssesmentsResponse;
import com.loginregistration.authtest.dataAccess.RiskAssesmentRepository;
import com.loginregistration.authtest.entities.RiskAssesment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiskAssesmentServiceImpl implements RiskAssesmentService {

    private RiskAssesmentRepository riskAssesmentRepository;

    @Autowired
    public RiskAssesmentServiceImpl(RiskAssesmentRepository riskAssesmentRepository) {
        this.riskAssesmentRepository = riskAssesmentRepository;
    }

    @Override
    public List<RiskAssesmentsResponse> getAll() {
        List<RiskAssesment> riskAssesments = riskAssesmentRepository.findAll();
        List<RiskAssesmentsResponse> riskAssesmentsResponses = new ArrayList<RiskAssesmentsResponse>();

        for (RiskAssesment riskAssesment : riskAssesments) {
            RiskAssesmentsResponse responseItem = new RiskAssesmentsResponse();

            responseItem.setId(riskAssesment.getId());
            responseItem.setTehlikeAdi(riskAssesment.getTehlikeAdi());
            responseItem.setYerEkipman(riskAssesment.getYerEkipman());
            responseItem.setMevcutTehlikeler(riskAssesment.getMevcutTehlikeler());
            responseItem.setOlusacakRiskler(riskAssesment.getOlusacakRiskler());
            responseItem.setMevcutOnlemler(riskAssesment.getMevcutOnlemler());
            responseItem.setMaruzKalanlar(riskAssesment.getMaruzKalanlar());
            responseItem.setSiddet(riskAssesment.getSiddet());
            responseItem.setOlasilik(riskAssesment.getOlasilik());
            responseItem.setRisk(riskAssesment.getRisk());
            responseItem.setAlinacakTedbirler(riskAssesment.getAlinacakTedbirler());
            responseItem.setSonSiddet(riskAssesment.getSonSiddet());
            responseItem.setSonOlasilik(riskAssesment.getSonOlasilik());
            responseItem.setSonRisk(riskAssesment.getSonRisk());
            responseItem.setWorkplaceId(riskAssesment.getWorkplace().getId());

            riskAssesmentsResponses.add(responseItem);
        }

        return riskAssesmentsResponses;
    }

    @Override
    public void add(CreateRiskAssesmentRequest createRiskAssesmentRequest) {
        RiskAssesment riskAssesment = new RiskAssesment();

        riskAssesment.setTehlikeAdi(createRiskAssesmentRequest.getTehlikeAdi());
        riskAssesment.setYerEkipman(createRiskAssesmentRequest.getYerEkipman());
        riskAssesment.setMevcutTehlikeler(createRiskAssesmentRequest.getMevcutTehlikeler());
        riskAssesment.setOlusacakRiskler(createRiskAssesmentRequest.getOlusacakRiskler());
        riskAssesment.setMevcutOnlemler(createRiskAssesmentRequest.getMevcutOnlemler());
        riskAssesment.setMaruzKalanlar(createRiskAssesmentRequest.getMaruzKalanlar());
        riskAssesment.setSiddet(createRiskAssesmentRequest.getSiddet());
        riskAssesment.setOlasilik(createRiskAssesmentRequest.getOlasilik());
        riskAssesment.setRisk(createRiskAssesmentRequest.getRisk());
        riskAssesment.setAlinacakTedbirler(createRiskAssesmentRequest.getAlinacakTedbirler());
        riskAssesment.setSonSiddet(createRiskAssesmentRequest.getSonSiddet());
        riskAssesment.setSonOlasilik(createRiskAssesmentRequest.getSonOlasilik());
        riskAssesment.setSonRisk(createRiskAssesmentRequest.getSonRisk());


        this.riskAssesmentRepository.save(riskAssesment);
    }

    @Override
    public List<RiskAssesmentsResponse> getAllByWorkplaceId(int workplaceId) {
        return this.riskAssesmentRepository.findAllByWorkplaceId(workplaceId).stream()
                .map(riskAssesment -> new RiskAssesmentsResponse(riskAssesment.getId(), riskAssesment.getTehlikeAdi(), riskAssesment.getYerEkipman(),
                        riskAssesment.getMevcutTehlikeler(), riskAssesment.getOlusacakRiskler(), riskAssesment.getMevcutOnlemler(), riskAssesment.getMaruzKalanlar(),
                        riskAssesment.getSiddet(), riskAssesment.getOlasilik(), riskAssesment.getRisk(), riskAssesment.getAlinacakTedbirler(),
                        riskAssesment.getSonSiddet(), riskAssesment.getSonOlasilik(), riskAssesment.getSonRisk(),
                        riskAssesment.getWorkplace().getId()))
                .collect(Collectors.toList());
    }
}
