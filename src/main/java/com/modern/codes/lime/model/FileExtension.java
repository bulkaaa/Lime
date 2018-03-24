package com.modern.codes.lime.model;

public enum FileExtension {
    JPG(0), PNG(1), GIF(2);

    FileExtension(final int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }

    public FileExtension getNext() {
        return get(this.id + 1);
    }

    public static FileExtension getFirst() {
        return get(0);
    }

    public static FileExtension get(final int id) {
        for (final FileExtension fileExtension : values()) {
            if (fileExtension.getValue() == id) {
                return fileExtension;
            }
        }
        return null;
    }

    public static boolean exist(final String str) {
        for (final FileExtension fe : values()) {
            if (fe.name()
                  .equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
    private final int id;
}
