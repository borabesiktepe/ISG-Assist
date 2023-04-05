package com.borabesiktepe.isgassist.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkplacesResponse {
	private int id;
	private String name;
	private String description;
	private int userId;
}