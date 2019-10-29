package ru.sergei.komarov.chatbot.server.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.Collection;
import java.util.List;

import static com.google.gson.JsonParser.parseString;

public class GsonConverter {

    private static final Gson GSON;

    static {
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }

    public static <T> JsonObject objectToJsonObject(T object) {
        return GSON.toJsonTree(
                object,
                new TypeToken<T>() {
                }.getType()
        ).getAsJsonObject();
    }

    public static <T> T jsonObjectToObject(JsonObject jsonObject) {
        return GSON.fromJson(
                jsonObject,
                new TypeToken<T>() {
                }.getType()
        );
    }

    public static <T> JsonArray collectionToJsonArray(Collection<T> collection) {
        return GSON.toJsonTree(
                collection,
                new TypeToken<Collection<T>>() {
                }.getType()
        ).getAsJsonArray();
    }

    public static <T> List<T> jsonArrayToList(JsonArray jsonArray) {
        return GSON.fromJson(
                jsonArray,
                new TypeToken<List<T>>() {
                }.getType()
        );
    }

    public static JsonObject parseToJsonObject(String string) {
        return parse(string).getAsJsonObject();
    }

    public static JsonArray parseToJsonArray(String string) {
        return parse(string).getAsJsonArray();
    }

    public static JsonElement parse(String string) {
        return parseString(string);
    }
}
