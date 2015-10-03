package com.nklmish.cs.catalogs.integration

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.nklmish.cs.catalogs.model.Comment
import com.nklmish.cs.catalogs.model.Price
import com.nklmish.cs.catalogs.model.Product
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@Slf4j
class ProductBuilder {
    RestTemplate restTemplate
    ServiceUriResolver serviceUriResolver

    @Autowired
    ProductBuilder(ServiceUriResolver serviceUriResolver) {
        this.restTemplate = new RestTemplate()
        this.serviceUriResolver = serviceUriResolver
    }

    @HystrixCommand(fallbackMethod = "productFallback")
    Product fetchProduct(int id) {
        String uri = serviceUriResolver.resolve("product-service") + "/product/${id}"
        log.debug("fetching product {} from {} ", id, uri)
        return restTemplate.getForObject(uri, Product)
    }

    @SuppressWarnings("unused")
    static Product productFallback(int productId) {
        log.error("fallback method invoked, hystrix command failed to retrieve product via product service")
        return new Product(name: "Demo Product")
    }

    @HystrixCommand(fallbackMethod = "priceFallback")
    Price fetchPrice(int productId) {
        String uri = serviceUriResolver.resolve("price-service") + "/price/${productId}"
        log.debug("fetching price for product {} from {} ", productId, uri)
        return restTemplate.getForObject(uri, Price)
    }

    @SuppressWarnings("unused")
    static Price priceFallback(int productId) {
        log.error("fallback method invoked, hystrix command failed to retrieve price via price service")
        return null
    }

    @HystrixCommand(fallbackMethod = "commentFallback")
    List<Comment> fetchComments(int productId) {
        String uri = serviceUriResolver.resolve("comment-service") + "/comment/${productId}"
        log.debug("fetching comments for product {} from {} ", productId, uri)
        return restTemplate.getForObject(uri, List)
    }

    @SuppressWarnings("unused")
    static List<Comment> commentFallback(int productId) {
        log.error("fallback method invoked, hystrix command failed to retrieve comments via comment service")
        return [] //return empty list of comments
    }

}
