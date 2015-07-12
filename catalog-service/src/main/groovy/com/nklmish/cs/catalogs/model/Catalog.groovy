package com.nklmish.cs.catalogs.model

class Catalog {
    int id
    Product product

    Catalog(final int id, final Product product) {
        this.id = id
        this.product = product
    }
}
