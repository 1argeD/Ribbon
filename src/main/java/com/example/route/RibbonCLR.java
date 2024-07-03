package com.example.route;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.cloud.client.discovery.DiscoveryClient;



@Component
public class RibbonCLR implements CommandLineRunner {
    private final DiscoveryClient discoveryClient;
    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    public RibbonCLR(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public void run(String... args) throws Exception {
        String serviceId = "greeting=-service";
//        List<Server> servers = this.discoveryClient
//                .getInstances(serviceId).stream()
//                .map(si -> new Server(si.getHost(), si.getPort()))
//                .collect(Collectors.toList());
//        IRule roundRobin = new RoundRobinRolue();
//
//        BaseLoadBa
    }

}
