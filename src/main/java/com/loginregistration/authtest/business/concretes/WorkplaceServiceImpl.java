package com.loginregistration.authtest.business.concretes;

import com.loginregistration.authtest.business.abstracts.WorkplaceService;
import com.loginregistration.authtest.business.requests.CreateWorkplaceRequest;
import com.loginregistration.authtest.business.responses.WorkplacesResponse;
import com.loginregistration.authtest.dataAccess.WorkplaceRepository;
import com.loginregistration.authtest.entities.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkplaceServiceImpl implements WorkplaceService {

	private WorkplaceRepository workplaceRepository;
	
	@Autowired
	public WorkplaceServiceImpl(WorkplaceRepository workplaceRepository) {
		this.workplaceRepository = workplaceRepository;
	}
	
	@Override
	public List<WorkplacesResponse> getAll() {
		List<Workplace> workplaces = workplaceRepository.findAll();
		List<WorkplacesResponse> workplacesResponses = new ArrayList<WorkplacesResponse>();
		
		for (Workplace workplace : workplaces) {
			WorkplacesResponse responseItem = new WorkplacesResponse();

			responseItem.setId(workplace.getId());
			responseItem.setName(workplace.getWorkplaceName());
			responseItem.setDescription(workplace.getWorkplaceDescription());
			responseItem.setUserId(workplace.getUser().getId());

			workplacesResponses.add(responseItem);
		}
		
		return workplacesResponses;
	}

	@Override
	public void add(CreateWorkplaceRequest createWorkplaceRequest) {
		Workplace workplace = new Workplace();

		workplace.setWorkplaceName(createWorkplaceRequest.getName());
		//workplace.setUser(createWorkplaceRequest.getUserId());
		
		this.workplaceRepository.save(workplace);
	}

	@Override
	public List<WorkplacesResponse> getAllByUserId(int userId) {
		return this.workplaceRepository.findAllByUserId(userId).stream()
				.map(workplace -> new WorkplacesResponse(workplace.getId(), workplace.getWorkplaceName(), workplace.getWorkplaceDescription(), workplace.getUser().getId()))
				.collect(Collectors.toList());
	}

}
