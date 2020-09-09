package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import exception.JsonException;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class JsonHelper {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String toJson(T data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException var2) {
            throw new JsonException("toJson failed.", var2);
        }
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        if (tClass.equals(String.class)) {
            return (T) json;
        } else if (json != null && !"".equals(json)) {
            try {
                return MAPPER.readValue(json, tClass);
            } catch (IOException var3) {
                throw new JsonException("fromJson failed.", var3);
            }
        } else {
            return null;
        }
    }

    public static <L, P> List<P> fromJsonToList(String json, Class<L> listClass, Class<P> pojoClass) {
        if (json != null && !"".equals(json)) {
            try {
                TypeFactory typeFactory = MAPPER.getTypeFactory();
                JavaType type = typeFactory.constructParametricType(listClass, new Class[]{pojoClass});
                return (List) MAPPER.readValue(json, type);
            } catch (IOException var5) {
                throw new JsonException("fromJsonToList failed.", var5);
            }
        } else {
            return Collections.emptyList();
        }
    }

    public static <M, K, V> Map<K, V> fromJsonToMap(String json, Class<M> mapClass, Class<K> keyClass, Class<V> valueClass) {
        if (json != null && !"".equals(json)) {
            try {
                TypeFactory typeFactory = MAPPER.getTypeFactory();
                JavaType type = typeFactory.constructParametricType(mapClass, new Class[]{keyClass, valueClass});
                return (Map) MAPPER.readValue(json, type);
            } catch (IOException var6) {
                throw new JsonException("fromJsonToMap failed.", var6);
            }
        } else {
            return Collections.emptyMap();
        }
    }

    public static <T> T readFromClasspath(String path, Class<T> tClass) {
        URL resource = JsonHelper.class.getClassLoader().getResource(path);
        if (resource == null) {
            return null;
        } else {
            try {
                return MAPPER.readValue(resource, tClass);
            } catch (IOException var4) {
                throw new JsonException("fromClasspath failed.", var4);
            }
        }
    }

    private JsonHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    static {
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
