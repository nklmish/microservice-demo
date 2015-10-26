package com.nklmish.ps.products.boundary

import com.nklmish.ps.products.model.Product
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.sleuth.Trace
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.TimeUnit

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@RequestMapping("/product")
@Slf4j
class ProductService {

    Trace trace

    @Autowired
    ProductService(Trace trace) {
        this.trace = trace
    }

    @RequestMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    Product findProduct(@PathVariable Integer id) {
        log.debug("generated new product with {}", id)
        this.trace.addAnnotation("fetching-product with id", id.toString())
        TimeUnit.SECONDS.sleep(2)
        return new Product(id, "Product-A", "super cool product")
    }
}
