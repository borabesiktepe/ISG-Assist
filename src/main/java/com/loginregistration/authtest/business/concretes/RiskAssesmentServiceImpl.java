package com.loginregistration.authtest.business.concretes;

import com.loginregistration.authtest.business.abstracts.RiskAssesmentService;
import com.loginregistration.authtest.business.requests.CreateRiskAssesmentRequest;
import com.loginregistration.authtest.business.responses.RiskAssesmentsResponse;
import com.loginregistration.authtest.dataAccess.RiskAssesmentRepository;
import com.loginregistration.authtest.dataAccess.WorkplaceRepository;
import com.loginregistration.authtest.entities.Company;
import com.loginregistration.authtest.entities.RiskAssesment;
import com.loginregistration.authtest.entities.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RiskAssesmentServiceImpl implements RiskAssesmentService {

    @Autowired
    private RiskAssesmentRepository riskAssesmentRepository;

    @Autowired
    private WorkplaceRepository workplaceRepository;


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
            responseItem.setDegerlendirmeTarihi(riskAssesment.getDegerlendirmeTarihi());
            responseItem.setWorkplaceId(riskAssesment.getWorkplace().getId());

            riskAssesmentsResponses.add(responseItem);
        }

        return riskAssesmentsResponses;
    }

    @Override
    public void add(CreateRiskAssesmentRequest createRiskAssesmentRequest) {
        Workplace workplace = workplaceRepository.findById(createRiskAssesmentRequest.getWorkplaceId()).orElseThrow(() -> new RuntimeException("Workplace ID bulunamadı."));

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
        riskAssesment.setDegerlendirmeTarihi(createRiskAssesmentRequest.getDegerlendirmeTarihi());
        riskAssesment.setWorkplace(workplace);

        this.riskAssesmentRepository.save(riskAssesment);
    }

    @Override
    public List<RiskAssesmentsResponse> getAllByWorkplaceId(int workplaceId) {
        return this.riskAssesmentRepository.findAllByWorkplaceId(workplaceId).stream()
                .map(riskAssesment -> new RiskAssesmentsResponse(riskAssesment.getId(), riskAssesment.getTehlikeAdi(), riskAssesment.getYerEkipman(),
                        riskAssesment.getMevcutTehlikeler(), riskAssesment.getOlusacakRiskler(), riskAssesment.getMevcutOnlemler(), riskAssesment.getMaruzKalanlar(),
                        riskAssesment.getSiddet(), riskAssesment.getOlasilik(), riskAssesment.getRisk(), riskAssesment.getAlinacakTedbirler(),
                        riskAssesment.getSonSiddet(), riskAssesment.getSonOlasilik(), riskAssesment.getSonRisk(), riskAssesment.getDegerlendirmeTarihi(),
                        riskAssesment.getWorkplace().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Integer workplaceId, CreateRiskAssesmentRequest createRiskAssesmentRequest) {
        RiskAssesment updateRiskAssesment = riskAssesmentRepository.findByWorkplaceId(workplaceId)
                .orElseThrow(() -> new RuntimeException("Risk Değerlendirme bulunmadı: " + workplaceId));

        updateRiskAssesment.setTehlikeAdi(createRiskAssesmentRequest.getTehlikeAdi());
        updateRiskAssesment.setYerEkipman(createRiskAssesmentRequest.getYerEkipman());
        updateRiskAssesment.setMevcutTehlikeler(createRiskAssesmentRequest.getMevcutTehlikeler());
        updateRiskAssesment.setOlusacakRiskler(createRiskAssesmentRequest.getOlusacakRiskler());
        updateRiskAssesment.setMevcutOnlemler(createRiskAssesmentRequest.getMevcutOnlemler());
        updateRiskAssesment.setMaruzKalanlar(createRiskAssesmentRequest.getMaruzKalanlar());
        updateRiskAssesment.setSiddet(createRiskAssesmentRequest.getSiddet());
        updateRiskAssesment.setOlasilik(createRiskAssesmentRequest.getOlasilik());
        updateRiskAssesment.setRisk(createRiskAssesmentRequest.getRisk());
        updateRiskAssesment.setAlinacakTedbirler(createRiskAssesmentRequest.getAlinacakTedbirler());
        updateRiskAssesment.setSonSiddet(createRiskAssesmentRequest.getSonSiddet());
        updateRiskAssesment.setSonOlasilik(createRiskAssesmentRequest.getSonOlasilik());
        updateRiskAssesment.setSonRisk(createRiskAssesmentRequest.getSonRisk());
        updateRiskAssesment.setDegerlendirmeTarihi(createRiskAssesmentRequest.getDegerlendirmeTarihi());

        riskAssesmentRepository.save(updateRiskAssesment);
    }
}
