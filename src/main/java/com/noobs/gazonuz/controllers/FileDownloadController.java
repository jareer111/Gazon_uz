package com.noobs.gazonuz.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;

//@Controller
//@RequestMapping( "/file" )
//public class FileDownloadController {


//    @GetMapping()
//    public ResponseEntity<?> downloadFile(@RequestParam( "path" ) String path) {
//
//
//        Resource resource = null;
//        try {
//            InputStream stream = new FileInputStream(path);
//            resource = new InputStreamResource(stream);
//        } catch ( IOException e ) {
//            return ResponseEntity.internalServerError().build();
//
//        }
//
//
//        String contentType = "application/octet-stream";
//        String headerValue = "filename=\"" + resource.getFilename() + "\"";
//
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))/*.*//*header("Content-Disposition" , "attachment; filename=" + path)*/.body(resource);
//    }

//    @GetMapping( "/file" )
//    public ResponseEntity<ByteArrayResource> displayImage(@RequestParam( "path" ) String path) throws IOException {
//        File file = new File(path);
//        byte[] data = Files.readAllBytes(file.toPath());
//        ByteArrayResource resource = new ByteArrayResource(data);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type" , "image/jpeg");
//        ResponseEntity<ByteArrayResource> response = ResponseEntity.ok().headers(headers).contentLength(data.length).body(resource);
//        return response;
//    }
//}