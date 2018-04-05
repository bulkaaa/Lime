package com.modern.codes.lime.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface Storage service.
 */
public interface IStorageService {

    /**
     * Init.
     */
    void init();

    /**
     * Store.
     *
     * @param file the file
     */
    void store(MultipartFile file);

    /**
     * Load all stream.
     *
     * @return the stream
     */
    Stream<Path> loadAll();

    /**
     * Load path.
     *
     * @param filename the filename
     * @return the path
     */
    Path load(String filename);

    /**
     * Load as resource resource.
     *
     * @param filename the filename
     * @return the resource
     */
    Resource loadAsResource(String filename);

    /**
     * Load as bytes byte [ ].
     *
     * @param filename the filename
     * @return the byte [ ]
     */
    byte[] loadAsBytes(String filename);

    /**
     * Delete all.
     */
    void deleteAll();

    /**
     * Delete.
     *
     * @param filename the filename
     */
    public void delete(final String filename);
}