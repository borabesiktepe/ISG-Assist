package com.borabesiktepe.isgassist.webApi.controllers;

import com.borabesiktepe.isgassist.business.abstracts.DocumentService;
import com.borabesiktepe.isgassist.business.requests.CreateDocumentRequest;
import com.borabesiktepe.isgassist.business.responses.DocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentsRestController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/getall")
    public List<DocumentResponse> getAllByWorkplaceId(@RequestParam int workplaceId) {
        return documentService.getAllByWorkplaceId(workplaceId);
    }
}
