package com.nklmish.cs.catalogs.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
class Price {
    BigDecimal amount
}
