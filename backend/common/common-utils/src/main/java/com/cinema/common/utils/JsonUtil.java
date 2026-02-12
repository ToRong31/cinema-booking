package com.cinema.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error converting object to JSON", e);
            return null;
        }
    }
    
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Error converting JSON to object", e);
            return null;
        }
    }
}
