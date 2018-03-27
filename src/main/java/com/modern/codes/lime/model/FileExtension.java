package com.modern.codes.lime.model;

public enum FileExtension {
    JPG(0), PNG(1), GIF(2);

    FileExtension(final int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
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
