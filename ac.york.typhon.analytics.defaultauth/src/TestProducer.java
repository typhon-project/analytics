import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;

public class TestProducer {

	public static void main(String[] args) {

		Properties prodProps = new Properties();
		prodProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
		prodProps.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
		prodProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		prodProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSchema.class.getName());

		KafkaProducer<Long, PreEvent> producer = new KafkaProducer<Long, PreEvent>(prodProps);

		PreEvent event = new PreEvent();
		event.setUser("Nick");
		ProducerRecord<Long, PreEvent> proRecord = new ProducerRecord<Long, PreEvent>("PRE", event);
		try {
			producer.send(proRecord).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}