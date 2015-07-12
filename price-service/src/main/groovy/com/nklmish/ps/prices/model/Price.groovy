package com.nklmish.ps.prices.model

import groovy.transform.Immutable

@Immutable
class Price {
    int id
    int productId
    BigDecimal amount
}
