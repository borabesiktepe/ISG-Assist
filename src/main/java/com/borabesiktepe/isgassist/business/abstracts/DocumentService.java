package com.borabesiktepe.isgassist.business.abstracts;

import com.borabesiktepe.isgassist.business.requests.CreateDocumentRequest;
import com.borabesiktepe.isgassist.business.responses.DocumentResponse;

import java.util.List;

public interface DocumentService {
	void add(CreateDocumentRequest createDocumentRequest);
	List<DocumentResponse> getAllByWorkplaceId(int workplaceId);
}