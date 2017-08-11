package de.robinschuerer.kafka.producer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDataProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataProducer.class);

	@Autowired
	private KafkaTemplate<Integer, String> template;


	private final CountDownLatch latch = new CountDownLatch(3);

//	@PostConstruct
	public void setup() throws InterruptedException {
		produce();
	}

	private void produce() throws InterruptedException {

		this.template.send("myTopic", "foo1");
		this.template.send("myTopic", "foo2");
		this.template.send("myTopic", "foo3");
		latch.await(60, TimeUnit.SECONDS);
		LOGGER.info("All received");

	}
}
