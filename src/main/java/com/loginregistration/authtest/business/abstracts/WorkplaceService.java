package com.loginregistration.authtest.business.abstracts;

import com.loginregistration.authtest.business.requests.CreateWorkplaceRequest;
import com.loginregistration.authtest.business.responses.WorkplacesResponse;

import java.util.List;

public interface WorkplaceService {
	List<WorkplacesResponse> getAll();
	void add(CreateWorkplaceRequest createWorkplaceRequest);

	List<WorkplacesResponse> getAllByUserId(int user);
}