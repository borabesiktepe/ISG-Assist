package com.borabesiktepe.isgassist.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDocumentRequest {
	private String documentName;
	private int workplaceId;
}
