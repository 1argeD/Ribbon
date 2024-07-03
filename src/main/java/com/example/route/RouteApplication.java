package com.example.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@EnableDiscoveryClient
@SpringBootApplication
public class RouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouteApplication.class, args);
    }


}
@RestController
class GreetingRestController {
    @RequestMapping(method = RequestMethod.GET, value = "/hi/{name}")
    Map<String, String> hi(@PathVariable String name, @RequestHeader(value = "X-CNJ-Name", required = false)  Optional<String> cn)  {
        String resolveName = cn.orElse(name);
        return Collections.singletonMap("greeting", "Hello, " + resolveName + "!");
    }
}

