package com.borabesiktepe.isgassist.business.concretes;

import com.borabesiktepe.isgassist.business.requests.CreateRiskAssesmentRequest;
import com.borabesiktepe.isgassist.business.responses.*;
import com.borabesiktepe.isgassist.dataAccess.RiskAssesmentRepository;
import com.borabesiktepe.isgassist.dataAccess.WorkplaceRepository;
import com.borabesiktepe.isgassist.entities.RiskAssesment;
import com.borabesiktepe.isgassist.business.abstracts.RiskAssesmentService;
import com.borabesiktepe.isgassist.entities.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
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
    public void update(Integer id, CreateRiskAssesmentRequest createRiskAssesmentRequest) {
        RiskAssesment updateRiskAssesment = riskAssesmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Risk Değerlendirme bulunmadı: " + id));

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

    @Override
    public void delete(int id) {
        RiskAssesment riskAssesment = riskAssesmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Risk Değerlendirme bulunmadı: " + id));

        riskAssesmentRepository.delete(riskAssesment);
    }

    @Override
    public List<RiskAssesmentTehlikeCount> enTekrarEdenTehlikeler(int workplaceId) {
        List<RiskAssesment> riskList = riskAssesmentRepository.findAllByWorkplaceId(workplaceId);
        Map<String, List<RiskAssesment>> mapByTehlikeAdi = riskList.stream().collect(Collectors.groupingBy(riskAssesment -> riskAssesment.getTehlikeAdi()));

        List<RiskAssesmentTehlikeCount> response = new ArrayList<>();

        mapByTehlikeAdi.forEach((tehlikeAdi, riskAssesments) -> {
            RiskAssesmentTehlikeCount riskAssesmentTehlikeCount = new RiskAssesmentTehlikeCount(tehlikeAdi, riskAssesments.size());
            response.add(riskAssesmentTehlikeCount);
        });

        return response;
    }

    @Override
    public List<RiskAssesmentYerEkipmanCount> enTehlikeliYerEkipmanlar(int workplaceId) {
        List<RiskAssesment> riskList = riskAssesmentRepository.findAllByWorkplaceId(workplaceId);
        Map<String, List<RiskAssesment>> mapByYerEkipman = riskList.stream().collect(Collectors.groupingBy(riskAssesment -> riskAssesment.getYerEkipman()));

        List<RiskAssesmentYerEkipmanCount> response = new ArrayList<>();

        //SUM işlemleri için
        //int toplam = 0;
        mapByYerEkipman.forEach((yerEkipman, riskAssesments) -> {
            AtomicInteger toplam = new AtomicInteger();

//            riskAssesments.forEach(riskAssesment -> {
//                toplam.addAndGet(riskAssesment.getRisk());
//            });

            //ikinci parametre toplam.get() ekle
            RiskAssesmentYerEkipmanCount riskAssesmentYerEkipmanCount = new RiskAssesmentYerEkipmanCount(yerEkipman, riskAssesments.size());
            response.add(riskAssesmentYerEkipmanCount);
        });

        return response;
    }

    @Override
    public List<RiskAssesmentTehlikeRiskSum> tehlikeAdiToplamRisk(int workplaceId) {
        List<RiskAssesment> riskList = riskAssesmentRepository.findAllByWorkplaceId(workplaceId);
        Map<String, List<RiskAssesment>> mapByTelikeAdi = riskList.stream().collect(Collectors.groupingBy(riskAssesment -> riskAssesment.getTehlikeAdi()));

        List<RiskAssesmentTehlikeRiskSum> response = new ArrayList<>();

        mapByTelikeAdi.forEach((tehlikeAdi, riskAssesments) -> {
            AtomicInteger toplam = new AtomicInteger();

            riskAssesments.forEach(riskAssesment -> {
                toplam.addAndGet(riskAssesment.getRisk());
            });

            RiskAssesmentTehlikeRiskSum riskAssesmentTehlikeRiskSum = new RiskAssesmentTehlikeRiskSum(tehlikeAdi, toplam.get());
            response.add(riskAssesmentTehlikeRiskSum);
        });

        return response;
    }

    @Override
    public List<RiskAssesmentTehlikeSonRiskSum> tehlikeAdiToplamSonRisk(int workplaceId) {
        List<RiskAssesment> riskList = riskAssesmentRepository.findAllByWorkplaceId(workplaceId);
        Map<String, List<RiskAssesment>> mapByTelikeAdi = riskList.stream().collect(Collectors.groupingBy(riskAssesment -> riskAssesment.getTehlikeAdi()));

        List<RiskAssesmentTehlikeSonRiskSum> response = new ArrayList<>();

        mapByTelikeAdi.forEach((tehlikeAdi, riskAssesments) -> {
            AtomicInteger toplam = new AtomicInteger();

            riskAssesments.forEach(riskAssesment -> {
                toplam.addAndGet(riskAssesment.getSonRisk());
            });

            RiskAssesmentTehlikeSonRiskSum riskAssesmentTehlikeSonRiskSum = new RiskAssesmentTehlikeSonRiskSum(tehlikeAdi, toplam.get());
            response.add(riskAssesmentTehlikeSonRiskSum);
        });

        return response;
    }

    @Override
    public List<RiskAssesmentYerEkipmanRiskSum> yerEkipmanToplamRisk(int workplaceId) {
        List<RiskAssesment> riskList = riskAssesmentRepository.findAllByWorkplaceId(workplaceId);
        Map<String, List<RiskAssesment>> mapByYerEkipman = riskList.stream().collect(Collectors.groupingBy(riskAssesment -> riskAssesment.getYerEkipman()));

        List<RiskAssesmentYerEkipmanRiskSum> response = new ArrayList<>();

        mapByYerEkipman.forEach((yerEkipman, riskAssesments) -> {
            AtomicInteger toplam = new AtomicInteger();

            riskAssesments.forEach(riskAssesment -> {
                toplam.addAndGet(riskAssesment.getRisk());
            });

            RiskAssesmentYerEkipmanRiskSum riskAssesmentYerEkipmanRiskSum = new RiskAssesmentYerEkipmanRiskSum(yerEkipman, toplam.get());
            response.add(riskAssesmentYerEkipmanRiskSum);
        });

        return response;
    }

    @Override
    public List<RiskAssesmentYerEkipmanSonRiskSum> yerEkipmanToplamSonRisk(int workplaceId) {
        List<RiskAssesment> riskList = riskAssesmentRepository.findAllByWorkplaceId(workplaceId);
        Map<String, List<RiskAssesment>> mapByYerEkipman = riskList.stream().collect(Collectors.groupingBy(riskAssesment -> riskAssesment.getYerEkipman()));

        List<RiskAssesmentYerEkipmanSonRiskSum> response = new ArrayList<>();

        mapByYerEkipman.forEach((yerEkipman, riskAssesments) -> {
            AtomicInteger toplam = new AtomicInteger();

            riskAssesments.forEach(riskAssesment -> {
                toplam.addAndGet(riskAssesment.getSonRisk());
            });

            RiskAssesmentYerEkipmanSonRiskSum riskAssesmentYerEkipmanSonRiskSum = new RiskAssesmentYerEkipmanSonRiskSum(yerEkipman, toplam.get());
            response.add(riskAssesmentYerEkipmanSonRiskSum);
        });

        return response;
    }
}
