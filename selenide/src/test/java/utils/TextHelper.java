package utils;

public class TextHelper {
    public static String normalizeQuotes(String text) {
        return text.replace('«', '"').replace('»', '"');
    }

}