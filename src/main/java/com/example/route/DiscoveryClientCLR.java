package com.example.route;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

@Component
public class DiscoveryClientCLR implements CommandLineRunner  {
    private  final DiscoveryClient discoveryClient;
    private Log log = LogFactory.getLog(getClass());

    @Autowired
    public DiscoveryClientCLR(DiscoveryClient discoveryClient) {
    this.discoveryClient = discoveryClient;
    }

    @Override
    public void run(String... args) throws Exception {
    this.log.info("localServiceInstance");
    this.logServiceInstance(this.discoveryClient.getLocalServiceInstance());

    String serviceId = "greetingService";
    this.log.info(String.format("registered instances of '%s", serviceId));
    this.discoveryClient.getInstances(serviceId)
            .forEach(this::logServiceInstance);
    }

    private void logServiceInstance(ServiceInstance si) {
        String ms = String.format("host = %s, port = %s, service ID = %s", si.getHost(), si.getPort(), si.getServiceId());
        log.info(ms);
    }

}
