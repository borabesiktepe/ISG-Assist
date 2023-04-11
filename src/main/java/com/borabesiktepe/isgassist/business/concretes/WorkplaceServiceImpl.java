package com.borabesiktepe.isgassist.business.concretes;

import com.borabesiktepe.isgassist.business.abstracts.WorkplaceService;
import com.borabesiktepe.isgassist.business.requests.CreateWorkplaceRequest;
import com.borabesiktepe.isgassist.business.responses.WorkplacesResponse;
import com.borabesiktepe.isgassist.dataAccess.WorkplaceRepository;
import com.borabesiktepe.isgassist.security.SecurityUtils;
import com.borabesiktepe.isgassist.entities.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkplaceServiceImpl implements WorkplaceService {

	@Autowired
	private WorkplaceRepository workplaceRepository;

	@Override
	public List<WorkplacesResponse> getAllByUserId(int userId) {
		return this.workplaceRepository.findAllByUserId(userId).stream()
				.map(workplace -> new WorkplacesResponse(workplace.getId(), workplace.getWorkplaceName(), workplace.getWorkplaceDescription(), workplace.getUser().getId()))
				.collect(Collectors.toList());
	}

	@Override
	public void add(CreateWorkplaceRequest createWorkplaceRequest) {
		Workplace workplace = new Workplace();

		if (SecurityUtils.getUser().isPresent()) {
			workplace.setWorkplaceName(createWorkplaceRequest.getName());
			workplace.setWorkplaceDescription(createWorkplaceRequest.getDescription());
			workplace.setUser(SecurityUtils.getUser().get());
		}
		
		this.workplaceRepository.save(workplace);
	}

	public void update(Integer workplaceId, CreateWorkplaceRequest createWorkplaceRequest) {
		Workplace updateWorkplace = workplaceRepository.findById(workplaceId)
				.orElseThrow(() -> new RuntimeException("Workplace bulunmadı: " + workplaceId));

		updateWorkplace.setWorkplaceName(createWorkplaceRequest.getName());
		updateWorkplace.setWorkplaceDescription(createWorkplaceRequest.getDescription());

		workplaceRepository.save(updateWorkplace);
	}
}
