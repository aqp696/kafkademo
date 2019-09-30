package org.duckdns.aqueipo.kafkademo.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.duckdns.aqueipo.kafkademo.services.Producer;
import org.duckdns.aqueipo.kafkademo.services.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * The type Kafka controller.
 */
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    private final Producer producer;

    /**
     * The Rest service.
     */
    @Autowired
    RestService restService;

    /**
     * Instantiates a new Kafka controller.
     *
     * @param producer the producer
     */
    @Autowired
    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    /**
     * Send message to kafka topic.
     *
     * @param message the message
     */
    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }

    /**
     * Gets response.
     *
     * @param cliente the cliente
     * @return the response
     * @throws IOException      the io exception
     * @throws UnirestException the unirest exception
     */
    @GetMapping(value = "/get", produces = "application/json")
    public String getResponse(@RequestParam(name = "cliente", required = false) String cliente) throws IOException, UnirestException {
        return  restService.get("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", cliente);
    }
}
