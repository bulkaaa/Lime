package com.modern.codes.lime.model;

/**
 * The enum File extension.
 */
public enum FileExtension {
    /**
     * Jpg file extension.
     */
    JPG(0), /**
     * Png file extension.
     */
    PNG(1), /**
     * Gif file extension.
     */
    GIF(2);

    FileExtension(final int id) {
        this.id = id;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return id;
    }

    /**
     * Exist boolean.
     *
     * @param str the str
     * @return the boolean
     */
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
