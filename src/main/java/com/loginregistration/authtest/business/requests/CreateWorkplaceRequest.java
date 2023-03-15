package com.loginregistration.authtest.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkplaceRequest {
	private String name;
	private int userId;
}
