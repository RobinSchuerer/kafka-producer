package de.robinschuerer.kafka.producer.config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestDataProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataProducer.class);

	private final KafkaTemplate<Integer, String> template;


	private final CountDownLatch latch = new CountDownLatch(3);

	@Autowired
	public TestDataProducer(final KafkaTemplate<Integer, String> template) {
		this.template = template;

		produce();
	}

	private void produce(){

		try {
			LOGGER.info("sending test data!");

			this.template.send("myTopic", "foo1");
			this.template.send("myTopic", "foo2");
			this.template.send("myTopic", "foo3");
			latch.await(60, TimeUnit.SECONDS);
			LOGGER.info("All received");
		} catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}

	}
}
