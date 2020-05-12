import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EventSchema implements Serializer<PreEvent>, Deserializer<PreEvent> {
	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
    public byte[] serialize(String topic, PreEvent data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception) {
        System.out.println("Error in serializing object"+ data);
        }
        return retVal;
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

}
