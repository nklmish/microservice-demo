package com.nklmish.ps.prices.boundary

import com.nklmish.ps.prices.model.Price
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@RequestMapping("/price")
class PriceService {

    @RequestMapping(value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    def findComments(@PathVariable Integer productId) {
        new Price(1, productId, 1000.20)
    }
}
