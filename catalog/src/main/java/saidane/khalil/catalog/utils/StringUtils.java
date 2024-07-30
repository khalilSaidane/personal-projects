package saidane.khalil.catalog.utils;

public final class StringUtils {

    private StringUtils() {
    }

    public static String extractClassName(String fullClassName) {
        String[] words = fullClassName.split("\\.");
        String lastWord = words[words.length - 1];
        return lastWord.replace(">", "");
    }
}
