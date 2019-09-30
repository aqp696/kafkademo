package org.duckdns.aqueipo.kafkademo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * The type Consumer.
 */
@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    /**
     * Consume.
     *
     * @param message the message
     */
    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) {
        logger.info(String.format("$$ -> Consumed Message -> %s", message));
    }

}
