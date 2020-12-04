import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;

import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;
import ac.york.typhon.analytics.commons.datatypes.events.Slot;
import ac.york.typhon.analytics.commons.deserialization.Utilities;
import engineering.swat.typhonql.ast.ASTConversionException;
import engineering.swat.typhonql.ast.Request;
import engineering.swat.typhonql.ast.TyphonQLASTParser;

public class App {

	final static boolean INVERTED = false;

	public static void main(String[] args) {

		Properties consProps = new Properties();
		// Change both IPs to kafka:9092 for docker deployment (before exporting to push to Docker-hub)
		consProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
		consProps.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConstants.GROUP_ID_CONFIG);
		consProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		consProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
		consProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EventSchema.class.getName());

		Properties prodProps = new Properties();
		prodProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
		prodProps.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
		prodProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
		prodProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSchema.class.getName());

		KafkaProducer<Long, PreEvent> producer = new KafkaProducer<>(prodProps);
		KafkaConsumer<Long, PreEvent> consumer = new KafkaConsumer<>(consProps);

		consumer.subscribe(Collections.singletonList("PRE"));
		while (true) {
			ConsumerRecords<Long, PreEvent> consumerRecords = consumer.poll(1000);

			consumerRecords.forEach(record -> {
				PreEvent consumedRecord = null;
				try {
					consumedRecord = record.value();

					if (INVERTED) {
						Request request = null;
						try {
							request = TyphonQLASTParser.parseTyphonQLRequest((consumedRecord.getQuery()).toCharArray());
						} catch (ASTConversionException e1) {
							e1.printStackTrace();
						}
						Utilities util = new Utilities();
						String invertedQuery = "";
						try {
							invertedQuery = util.createInvertedSelect(request);
							if (!invertedQuery.equals("")) {
								consumedRecord.setInvertedNeeded(true);
								consumedRecord.setInvertedQuery(invertedQuery);
							}
						} catch (Exception e1) {
							System.err.println("error in inverting query");
							e1.printStackTrace();
						}
					}
					// This is an authorise all so here we authorise everything
//					consumedRecord.setInvertedNeeded(true);
					consumedRecord.setResultSetNeeded(true);
					consumedRecord.setAuthenticated(true);
//					consumedRecord.setInvertedQuery("from Product p select p.p_price, p.name, p.@id");
//					Slot slot = new Slot("Thanos Type", "Thanos Field", null);
//					consumedRecord.getSlots().add(slot);
//					System.out.println(consumedRecord);
					ProducerRecord<Long, PreEvent> proRecord = new ProducerRecord<Long, PreEvent>("AUTH",
							consumedRecord);
					producer.send(proRecord).get();
				} catch (InterruptedException | ExecutionException e) {
					System.err.println("error in sending record");
					e.printStackTrace();
				} catch (Exception e) {
					System.err.println("error in parsing into pre-event");
					e.printStackTrace();
				}

			});

			consumer.commitAsync();
		}
		
	}

}