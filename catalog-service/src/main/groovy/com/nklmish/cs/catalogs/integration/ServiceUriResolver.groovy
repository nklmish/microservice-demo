package com.nklmish.cs.catalogs.integration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import org.springframework.stereotype.Component

@Component
class ServiceUriResolver {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceUriResolver.class)

    LoadBalancerClient loadBalancerClient

    String defaultUrl = "http://127.0.0.1:8080"

    @Autowired
    ServiceUriResolver(final LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient
    }

    def resolve(String id) {
        try {
            loadBalancerClient.choose(id).uri.toString()
        } catch (RuntimeException e) {
            LOG.error("Failed to resolve service {}, details {}", id, e.getMessage())
            defaultUrl
        }
    }
}
