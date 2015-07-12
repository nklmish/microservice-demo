package com.nklmish.ps.products.boundary

import com.nklmish.ps.products.model.Product
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@RequestMapping("/product")
class ProductService {

    @RequestMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    def findProduct(@PathVariable Integer id) {
        return new Product(id, "Product-A", "super cool product")
    }
}
