package de.robinschuerer.kafka.producer.config;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestDataProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataProducer.class);

	private final KafkaTemplate<Integer, String> template;

	private static final String QUEUE_NAME = "kafka-test-01";

	@Autowired
	public TestDataProducer(final KafkaTemplate<Integer, String> template) {
		this.template = template;
		produce();
	}

	private void produce() {
		final String data = UUID.randomUUID().toString();
		LOGGER.info("sending test data: {}", data);

		this.template.send(QUEUE_NAME, data);
	}
}
