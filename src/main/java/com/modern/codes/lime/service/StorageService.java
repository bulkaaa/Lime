package com.modern.codes.lime.service;

import java.io.IOException;
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
import com.modern.codes.lime.model.FileExtension;

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
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (final MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public byte[] loadAsBytes(final String filename) {
        return Try.of(() -> Base64.getEncoder()
                                  .encode(ByteStreams.toByteArray(this.loadAsResource(filename)
                                                                      .getInputStream())))
                  .getOrElse(ArrayUtils.EMPTY_BYTE_ARRAY);
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void delete(final String filename) {
        deleteFile(filename, FileExtension.getFirst());
    }

    private Boolean deleteFile(final String filename, final FileExtension fe) {
        if (fe == null) {
            return false;
        }
        final Path file = load(filename + '.' + fe);
        try {
            Files.delete(file);
            return true;
        } catch (final IOException e) {
            return deleteFile(filename, fe.getNext());
        }
    }
    private final Path rootLocation;
}
