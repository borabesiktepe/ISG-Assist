package com.borabesiktepe.isgassist.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponse {
	private int id;
	private String documentName;
	private int workplaceId;
}