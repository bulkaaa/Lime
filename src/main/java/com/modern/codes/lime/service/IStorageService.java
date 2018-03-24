package com.modern.codes.lime.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    byte[] loadAsBytes(String filename);

    void deleteAll();

    public void delete(final String filename);
}