package com.nklmish.cs.catalogs.integration

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import org.springframework.stereotype.Component

@Component
@Slf4j
class ServiceUriResolver {

    LoadBalancerClient loadBalancerClient

    static final String defaultUrl = "http://127.0.0.1:8080"

    @Autowired
    ServiceUriResolver(final LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient
    }

    String resolve(String id) {
        try {
            return loadBalancerClient.choose(id).uri.toString()
        } catch (RuntimeException e) {
            log.error("Failed to resolve service {}, details {}", id, e.message)
            return defaultUrl
        }
    }
}
