package com.borabesiktepe.isgassist.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkplaceRequest {
	private String name;
	private String description;
	private int userId;
}