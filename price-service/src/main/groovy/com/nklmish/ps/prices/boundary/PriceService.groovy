package com.nklmish.ps.prices.boundary

import com.codahale.metrics.Counter
import com.codahale.metrics.MetricRegistry
import com.nklmish.ps.prices.model.Price
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.PostConstruct

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@RequestMapping("/price")
@Slf4j
class PriceService {

    public static final BigDecimal PROFIT_THRESHOLD = BigDecimal.valueOf(1200.0)

    @Autowired
    MetricRegistry metrics

    private  Counter expensiveProducts

    private int randomness = new Random().nextInt((100 - 10) + 1) + 10

    @RequestMapping(value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    Price findComments(@PathVariable Integer productId) {
        log.debug("retrieving price for product {}", productId)
        BigDecimal calculatedPrice = BigDecimal.valueOf(productId).multiply(randomness)

        reportIfProfitable(calculatedPrice)

        return new Price(1, productId, calculatedPrice)
    }

    private void reportIfProfitable(BigDecimal calculatedPrice) {
        if (calculatedPrice.compareTo(PROFIT_THRESHOLD) > 0) {
            expensiveProducts.inc()
        }
    }

    @PostConstruct
    public void registerCounter() {
        expensiveProducts = metrics.counter("expensive-products")
    }
}
