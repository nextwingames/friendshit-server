package org.nextwin.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonManager {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 객체를 직렬화
	 * @param object
	 * @return Byte array
	 * @throws JsonProcessingException
	 */
	public static byte[] objectToBytes(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsBytes(object);
	}
	
	/**
	 * 객체를 역직렬화
	 * @param bytes
	 * @param classType
	 * @return Object
	 * @throws IOException
	 */
	public static Object bytesToObject(byte[] bytes, Class<?> classType) throws IOException {
		String json = new String(bytes);
		System.out.println("json:" + json);
		return objectMapper.readValue(json, classType);
	}

}
