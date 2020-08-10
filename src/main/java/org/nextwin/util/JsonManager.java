package org.nextwin.util;

import java.io.IOException;

import org.nextwin.protocol.Header;

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
	 * 헤더를 직렬화, byte 배열의 길이를 26으로 맞춤
	 * @param header
	 * @return
	 * @throws JsonProcessingException
	 */
	public static byte[] objectToBytes(Header header) throws JsonProcessingException {
		String json = objectMapper.writeValueAsString(header);
		
		if(header.getMsgType() < 10)
			json += ' ';
		if(header.getLength() < 10)
			json += ' ';
		
		return json.getBytes();
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
		return objectMapper.readValue(json, classType);
	}

}
