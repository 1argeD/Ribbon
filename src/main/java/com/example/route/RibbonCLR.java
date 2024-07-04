package com.example.route;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.RoundRobinRule;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.net.URI;
import java.util.List;
import java.util.stream.IntStream;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;

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
        List<Server> servers = this.discoveryClient
                .getInstances(serviceId).stream()
                .map(si -> new Server(si.getHost(), si.getPort())).toList();
        IRule roundRobinRule = new RoundRobinRule();

        BaseLoadBalancer baseLoadBalancer = LoadBalancerBuilder.newBuilder()
                .withRule(roundRobinRule)
                .buildFixedServerListLoadBalancer(servers);

        IntStream.range(0,10).forEach(
                i->{
                    com.netflix.loadbalancer.Server server = baseLoadBalancer.chooseServer(servers);
                    URI uri = URI.create("http://" + server.getHost() + ":" + server.getPort()+"'/" );
                    log.info("resolved service : "+uri.toString());
                }
        );



    }

}
