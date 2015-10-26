package com.nklmish.ps.prices.boundary

import com.nklmish.ps.prices.model.Price
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.TimeUnit

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@RequestMapping("/price")
@Slf4j
class PriceService {

    private int randomness = new Random().nextInt((100 - 10) + 1) + 10

    @RequestMapping(value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    Price findComments(@PathVariable Integer productId) {
        log.debug("retrieving price for product {}", productId)
        BigDecimal calculatedPrice = BigDecimal.valueOf(productId).multiply(randomness)
        TimeUnit.SECONDS.sleep(2)
        return new Price(1, productId, calculatedPrice)
    }

}
