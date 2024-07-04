package com.example.route;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Component
public class LoadBalancedRestTemplate implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private  final Log log = LogFactory.getLog(getClass());

    public LoadBalancedRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String,  String> variables = Collections.singletonMap("name", "Cloud Natives!");

        ResponseEntity<JsonNode> response = this.restTemplate.getForEntity("//greeting-service/hi/{name}",
                JsonNode.class, variables);

        JsonNode body = response.getBody();
        String greeting = body.get("greeting").asText();
        log.info("greeting: "+greeting);
    }


}
