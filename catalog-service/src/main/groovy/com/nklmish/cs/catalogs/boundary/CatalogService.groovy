package com.nklmish.cs.catalogs.boundary

import com.nklmish.cs.catalogs.integration.ProductBuilder
import com.nklmish.cs.catalogs.model.Catalog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@RequestMapping("/catalog")
class CatalogService {

    ProductBuilder productBuilder

    @Autowired
    CatalogService(final ProductBuilder productBuilder) {
        this.productBuilder = productBuilder
    }

    @RequestMapping(value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    def findCatalog(@PathVariable Integer productId) {
        def product = productBuilder.fetchProduct(productId)
        product.setComments(productBuilder.fetchComments(productId))
        product.setPrice(productBuilder.fetchPrice(productId))

        new Catalog(productId, product)
    }
}
