package com.modern.codes.lime.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.modern.codes.lime.exception.StorageFileNotFoundException;
import com.modern.codes.lime.model.FileExtension;
import com.modern.codes.lime.service.IStorageService;

/**
 * The type File upload controller.
 */
@Controller
@RequestMapping(path = "/file_management")
public class FileUploadController {

    /**
     * Instantiates a new File upload controller.
     *
     * @param storageService the storage service
     */
    public FileUploadController(final IStorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Serve file response entity.
     *
     * @param filename the filename
     * @return the response entity
     */
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<byte[]> serveFile(@PathVariable final String filename) {

        final byte[] file = storageService.loadAsBytes(filename);
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + '"')
                             .body(file);
    }

    /**
     * Handle file upload response entity.
     *
     * @param file the file
     * @return the response entity
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") final MultipartFile file) {
        final String extension = file.getOriginalFilename()
                                     .split("\\.")[1];
        if (!FileExtension.exist(extension)) {
            return ResponseEntity.badRequest()
                                 .body("Incorrect extension.");
        }
        storageService.store(file);
        return ResponseEntity.ok()
                             .body("\"OK\"");
    }

    /**
     * Handle storage file not found response entity.
     *
     * @param exc the exc
     * @return the response entity
     */
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(final StorageFileNotFoundException exc) {
        return ResponseEntity.notFound()
                             .build();
    }

    /**
     * Delete file.
     *
     * @param filename the filename
     */
    @DeleteMapping("/{filename:.+}")
    @ResponseBody
    public void deleteFile(@PathVariable final String filename) {
        storageService.delete(filename);
    }

    private final IStorageService storageService;

}