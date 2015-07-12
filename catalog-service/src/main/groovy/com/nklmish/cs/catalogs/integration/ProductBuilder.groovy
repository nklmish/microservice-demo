package com.nklmish.cs.catalogs.integration

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.nklmish.cs.catalogs.model.Price
import com.nklmish.cs.catalogs.model.Product
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ProductBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(ProductBuilder.class)

    RestTemplate restTemplate
    ServiceUriResolver serviceUriResolver

    @Autowired
    ProductBuilder(ServiceUriResolver serviceUriResolver) {
        this.restTemplate = new RestTemplate()
        this.serviceUriResolver = serviceUriResolver
    }

    @HystrixCommand(fallbackMethod = "productFallback")
    def fetchProduct(int id) {
        def uri = serviceUriResolver.resolve("product-service") + "/product/${id}"
        LOG.debug("fetching product {} from {} ", id, uri)
        restTemplate.getForObject(uri, Product.class)
    }

    def productFallback(int productId) {
        LOG.error("fallback method invoked, hystrix command failed to retrieve product via product service")
        new Product(name: "Demo Product")
    }

    @HystrixCommand(fallbackMethod = "priceFallback")
    def fetchPrice(int productId) {
        def uri = serviceUriResolver.resolve("price-service") + "/price/${productId}"
        LOG.debug("fetching price for product {} from {} ", productId, uri)
        restTemplate.getForObject(uri, Price.class)
    }

    def priceFallback(int productId) {
        LOG.error("fallback method invoked, hystrix command failed to retrieve price via price service")
        null
    }

    @HystrixCommand(fallbackMethod = "commentFallback")
    def fetchComments(int productId) {
        def uri = serviceUriResolver.resolve("comment-service") + "/comment/${productId}"
        LOG.debug("fetching comments for product {} from {} ", productId, uri)
        restTemplate.getForObject(uri, List.class)
    }

    def commentFallback(int productId) {
        LOG.error("fallback method invoked, hystrix command failed to retrieve comments via comment service")
        [] //return empty list of comments
    }

}
