package com.modern.codes.lime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.modern.codes.lime.exception.StorageFileNotFoundException;
import com.modern.codes.lime.model.FileExtension;
import com.modern.codes.lime.service.StorageService;

@Controller
@RequestMapping(path="/file_management")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable final String filename) {

        final Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                          "attachment; filename=\"" + file.getFilename() + '"').body(file);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") final MultipartFile file) {
        final String extension = file.getOriginalFilename().split("\\.")[1];
        if(!FileExtension.exist(extension))
            return ResponseEntity.badRequest().body("Incorrect extension.");
        storageService.store(file);
        return ResponseEntity.ok().body("\"OK\"");
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(final StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{filename:.+}")
    @ResponseBody
    public void deleteFile(@PathVariable final String filename) {
        storageService.delete(filename);
    }

}