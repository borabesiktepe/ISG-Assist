package com.loginregistration.authtest.business.business.abstracts;

import com.loginregistration.authtest.business.business.requests.CreateWorkplaceRequest;
import com.loginregistration.authtest.business.business.responses.GetAllWorkplacesResponse;
import com.loginregistration.authtest.business.business.responses.WorkplacesResponse;

import java.util.List;

public interface WorkplaceService {
	List<GetAllWorkplacesResponse> getAll();
	void add(CreateWorkplaceRequest createWorkplaceRequest);

	List<WorkplacesResponse> getAllByUserId(int user);
}