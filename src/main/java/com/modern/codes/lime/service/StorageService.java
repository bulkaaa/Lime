package com.modern.codes.lime.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.ByteStreams;
import com.modern.codes.lime.config.StorageProperties;
import com.modern.codes.lime.exception.StorageException;
import com.modern.codes.lime.exception.StorageFileNotFoundException;

import io.vavr.control.Try;

@Service
public class StorageService implements IStorageService {

    @Autowired
    public StorageService(final StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        init();
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (final IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(final MultipartFile file) {
        final String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory " + filename);
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (final IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                        .filter(path -> !path.equals(this.rootLocation))
                        .map(this.rootLocation::relativize);
        } catch (final IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(final String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(final String filename) {
        try {
            final Path file = load(filename);
            final Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
               return loadAsResource("no_image.jpg");
            }
        } catch (final MalformedURLException e) {
            return loadAsResource("no_image.jpg");
        }
    }

    @Override
    public byte[] loadAsBytes(final String filename) {
        try {
            final InputStream is = this.loadAsResource(filename).getInputStream();
            final byte[] bytes = ByteStreams.toByteArray(is);
            is.close();
            return Try.of(() -> Base64.getEncoder()
                                      .encode(bytes))
                      .getOrElse(ArrayUtils.EMPTY_BYTE_ARRAY);
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void delete(final String filename) {
        deleteFile(filename);
    }

    private Boolean deleteFile(final String filename) {
        try {
            final Path file = load(filename);

            Files.delete(file);
            return true;
        } catch (final IOException e) {
            return false;
        }
    }
    private final Path rootLocation;
}
