package com.noobs.gazonuz.services;


import com.noobs.gazonuz.domains.Document;
import com.noobs.gazonuz.domains.DocumentRepository;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.upload.DocumentCreateDTO;
import com.noobs.gazonuz.utils.BaseUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    private Path rootPath;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        rootPath = Path.of("/home/otash/techcollection/IdeaProjects/Jakarta/gazon-uz/src/main/resources/downloads");
//        rootPath = Path.of("C:/Users/Muhammadjon/", "/uploads");
        if ( !Files.exists(rootPath) )
            Files.createDirectories(rootPath);
    }

    public Document createAndGet(DocumentCreateDTO dto , User user) {

        MultipartFile file = dto.getFile();
        Document doc = Document.builder()
                .mimeType(file.getContentType())
                .fileSize(file.getSize())
                .originalFileName(file.getOriginalFilename())
                .generatedFileName(BaseUtils.generateUniqueName(Objects.requireNonNull(file.getOriginalFilename())))
//                .user(user)
                .filePath(rootPath.toString())
                .extension(BaseUtils.generateUniqueName(Objects.requireNonNull(file.getOriginalFilename())))
                .build();
        documentRepository.save(doc);
        CompletableFuture.runAsync(() -> {
            Path path = rootPath.resolve(doc.getGeneratedFileName());
            try {
                Files.copy(file.getInputStream() , path , StandardCopyOption.REPLACE_EXISTING);
            } catch ( IOException e ) {
                throw new RuntimeException(e);
            }
        });
        return doc;
    }
}
