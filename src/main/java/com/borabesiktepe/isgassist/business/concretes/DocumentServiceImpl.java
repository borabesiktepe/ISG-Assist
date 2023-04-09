package com.borabesiktepe.isgassist.business.concretes;

import com.borabesiktepe.isgassist.business.abstracts.DocumentService;
import com.borabesiktepe.isgassist.business.requests.CreateDocumentRequest;
import com.borabesiktepe.isgassist.business.responses.DocumentResponse;
import com.borabesiktepe.isgassist.dataAccess.DocumentRepository;
import com.borabesiktepe.isgassist.dataAccess.WorkplaceRepository;
import com.borabesiktepe.isgassist.entities.Document;
import com.borabesiktepe.isgassist.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private WorkplaceRepository workplaceRepository;

	@Override
	public void add(CreateDocumentRequest createDocumentRequest) {
		Document document = new Document();

		document.setFileName(createDocumentRequest.getDocumentName());
		document.setWorkplace(workplaceRepository.findById(createDocumentRequest.getWorkplaceId()).get());

		this.documentRepository.save(document);
	}

	@Override
	public List<DocumentResponse> getAllByWorkplaceId(int workplaceId) {
		return this.documentRepository.findAllByWorkplaceId(workplaceId).stream()
				.map(document -> new DocumentResponse(document.getId(), document.getFileName(), document.getWorkplace().getId()))
				.collect(Collectors.toList());
	}
}
