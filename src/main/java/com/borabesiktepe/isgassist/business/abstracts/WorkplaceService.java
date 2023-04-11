package com.borabesiktepe.isgassist.business.abstracts;

import com.borabesiktepe.isgassist.business.responses.WorkplacesResponse;
import com.borabesiktepe.isgassist.business.requests.CreateWorkplaceRequest;

import java.util.List;

public interface WorkplaceService {
	List<WorkplacesResponse> getAllByUserId(int user);
	void add(CreateWorkplaceRequest createWorkplaceRequest);
	void update(Integer id, CreateWorkplaceRequest createWorkplaceRequest);
}