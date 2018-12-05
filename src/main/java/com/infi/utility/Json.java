package com.infi.utility;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class Json {
	private final static Logger logger = LoggerFactory.getLogger(Json.class);
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String serialize(Object object) {
		Writer write = new StringWriter();
		try {
			objectMapper.writeValue(write, object);
		} catch (JsonGenerationException e) {
			logger.error("JsonGenerationException when serialize object to json", e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException when serialize object to json", e);
		} catch (IOException e) {
			logger.error("IOException when serialize object to json", e);
		}
		return write.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String json, Class<T> clazz) {
		try {
			return (T) objectMapper.readValue(json, TypeFactory.rawClass(clazz));
		} catch (JsonParseException e) {
			logger.error("JsonParseException when deserialize json: " + System.lineSeparator() + json, e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException when deserialize json: " + System.lineSeparator() + json, e);
		} catch (IOException e) {
			logger.error("IOException when deserialize json: " + System.lineSeparator() + json, e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String json, TypeReference<T> typeRef) {
		try {
			return (T) objectMapper.readValue(json, typeRef);
		} catch (JsonParseException e) {
			logger.error("JsonParseException when deserialize json: " + System.lineSeparator() + json, e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException when deserialize json: " + System.lineSeparator() + json, e);
		} catch (IOException e) {
			logger.error("IOException when deserialize json: " + System.lineSeparator() + json, e);
		}
		return null;
	}
}
