package ru.sergei.komarov.chatbot.server.utils;

import java.util.Base64;

public class HashTool {

    private static Base64.Encoder base64Encoder;
    private static Base64.Decoder base64Decoder;

    static {
        base64Encoder = Base64.getEncoder();
        base64Decoder = Base64.getDecoder();
    }

    public static String hash(String value) {
        return base64Encoder.encodeToString(value.getBytes());
    }

    public static String unhash(String hashedValue) {
        return new String(base64Decoder.decode(hashedValue.getBytes()));
    }

    public static boolean isNullOrEmptyString(String string) {
        return string == null || string.trim().isEmpty();
    }
}
