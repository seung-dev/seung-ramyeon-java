package seung.kimchi.java;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import seung.kimchi.java.types.SLinkedHashMap;

public class SJson {

	public static String stringify(
			Object data
			, boolean is_pretty
			) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializerProvider().setNullKeySerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
				jsonGenerator.writeFieldName("");
			}
		});
		return objectMapper
				.setSerializationInclusion(Include.ALWAYS)
				.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
				.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
				.configure(SerializationFeature.INDENT_OUTPUT, is_pretty)
				.writeValueAsString(data)
				;
	}// end of stringify
	
	public static SLinkedHashMap parse_json(String data) throws JsonMappingException, JsonProcessingException {
		if(data == null || "".equals(data)) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializerProvider().setNullKeySerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
				jsonGenerator.writeFieldName("");
			}
		});
		return objectMapper
				.registerModule(
						new SimpleModule("seung", Version.unknownVersion())
						.addAbstractTypeMapping(Map.class, SLinkedHashMap.class)
						)
				.readValue(data, SLinkedHashMap.class)
				;
	}// end of parse_json
	
}
