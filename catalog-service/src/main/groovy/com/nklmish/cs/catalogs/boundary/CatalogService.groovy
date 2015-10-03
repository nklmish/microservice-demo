package com.nklmish.cs.catalogs.boundary

import com.nklmish.cs.catalogs.integration.ProductBuilder
import com.nklmish.cs.catalogs.model.Catalog
import com.nklmish.cs.catalogs.model.Product
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@RequestMapping("/catalog")
@Slf4j
class CatalogService {

    ProductBuilder productBuilder

    @Autowired
    CatalogService(final ProductBuilder productBuilder) {
        this.productBuilder = productBuilder
    }

    @RequestMapping(value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    Catalog findCatalog(@PathVariable Integer productId) {
        log.debug("retrieving catalog for productId {}", productId)
        Product product = productBuilder.fetchProduct(productId)
        product.with {
            comments = productBuilder.fetchComments(productId)
            price = productBuilder.fetchPrice(productId)
        }

        return new Catalog(productId, product)
    }
}
