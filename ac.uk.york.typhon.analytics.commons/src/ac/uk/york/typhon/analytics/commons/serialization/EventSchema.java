package ac.uk.york.typhon.analytics.commons.serialization;

import java.io.IOException;
import java.util.Map;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import ac.uk.york.typhon.analytics.commons.datatypes.events.Event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class EventSchema implements DeserializationSchema<Event>, Deserializer,
		SerializationSchema<Event>, Serializer {
	private static ObjectMapper objectMapper = new ObjectMapper()
			.registerModule(new JavaTimeModule());
	private Class<?> eventClass;

	public EventSchema() {
		// TODO Auto-generated constructor stub
	}

	public EventSchema(Class<?> eventClass) {
		this.eventClass = eventClass;
	}

	private byte[] convertObjectToByteArray(Object obj) {
		byte[] serializedDataArray = null;
		try {
			if (obj != null) {
				serializedDataArray = objectMapper.writeValueAsBytes(obj);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return serializedDataArray;
	}

	@Override
	public TypeInformation<Event> getProducedType() {

		return TypeExtractor.getForClass(Event.class);
	}

	@Override
	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object deserialize(String topic, byte[] data) {
		// Kafka Deserializer
		System.out.println("Unimplemented Kafka Deserializer");
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public Event deserialize(byte[] message) throws IOException {
		// Flink Deserializer

		Event event = null;
		try {

			if (message != null) {
				event = (Event) objectMapper
						.readValue(message, this.eventClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;

	}

	@Override
	public boolean isEndOfStream(Event nextElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] serialize(String topic, Object data) {
		// Kafka serializer

		return convertObjectToByteArray(data);
	}

	@Override
	public byte[] serialize(Event element) {
		// Flink serializer

		return convertObjectToByteArray(element);
	}

}
