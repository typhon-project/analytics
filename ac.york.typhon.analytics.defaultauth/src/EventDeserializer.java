import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EventDeserializer implements Deserializer<PreEvent> {
	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public PreEvent deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		PreEvent object = null;
		try {
			object = mapper.readValue(data, PreEvent.class);
		} catch (Exception exception) {
			System.out.println("Error in deserializing bytes " + exception);
		}
		return object;
	}

	@Override
	public void close() {
	}
}
