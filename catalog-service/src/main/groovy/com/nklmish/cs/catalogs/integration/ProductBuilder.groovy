package com.nklmish.cs.catalogs.integration

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.nklmish.cs.catalogs.model.Comment
import com.nklmish.cs.catalogs.model.Price
import com.nklmish.cs.catalogs.model.Product
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
@Slf4j
class ProductBuilder {
    RestTemplate restTemplate

    @Autowired
    ProductBuilder(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate
    }

    @HystrixCommand(fallbackMethod = "productFallback")
    Product fetchProduct(int id) {
        log.debug("fetching product {}", id)
        return restTemplate.getForObject("http://product-service/product/$id", Product)
    }

    static Product productFallback(int productId) {
        log.error("fallback method invoked, hystrix command failed to retrieve product via product service")
        return new Product(name: "Demo Product")
    }

    @HystrixCommand(fallbackMethod = "priceFallback")
    Price fetchPrice(int productId) {
        log.debug("fetching price for product {}", productId)
        return restTemplate.getForObject("http://price-service/price/$productId", Price)
    }

    static Price priceFallback(int productId) {
        log.error("fallback method invoked, hystrix command failed to retrieve price via price service")
        return null
    }

    @HystrixCommand(fallbackMethod = "commentFallback")
    List<Comment> fetchComments(int productId) {
        log.debug("fetching comments for product {}", productId)
        return restTemplate.getForObject("http://comment-service/comment/$productId", List)
    }

    static List<Comment> commentFallback(int productId) {
        log.error("fallback method invoked, hystrix command failed to retrieve comments via comment service")
        return [] //return empty list of comments
    }

}
