package com.borabesiktepe.isgassist.webApi.controllers;

import com.borabesiktepe.isgassist.business.abstracts.DocumentService;
import com.borabesiktepe.isgassist.business.requests.CreateDocumentRequest;
import com.borabesiktepe.isgassist.business.responses.DocumentResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class DocumentsController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/files/upload/{id}")
    public String uploadFile(@PathVariable int id, Model model, @RequestParam("file") MultipartFile file) {

        try {
            Resource resource = new ClassPathResource("static/files/");
            String absolutePath = resource.getFile().getAbsolutePath();

            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(absolutePath, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());

            CreateDocumentRequest createDocumentRequest = new CreateDocumentRequest();
            createDocumentRequest.setDocumentName(file.getOriginalFilename());
            createDocumentRequest.setWorkplaceId(id);

            documentService.add(createDocumentRequest);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/workplace_panel/" + id;
    }

    @GetMapping("/api/documents/getall/")
    public List<DocumentResponse> getAllByWorkplaceId(@RequestParam int workplaceId) {
        return documentService.getAllByWorkplaceId(workplaceId);
    }
}
