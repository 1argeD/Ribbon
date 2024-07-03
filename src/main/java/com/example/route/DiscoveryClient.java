package com.example.route;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Lazy;

import java.security.Provider;
import java.util.List;

public interface DiscoveryClient {
    String description();
    ServiceInstance getLocalServiceInstance();
    List<ServiceInstance> getInstances(String var1);
    List<String> getServices();
}
