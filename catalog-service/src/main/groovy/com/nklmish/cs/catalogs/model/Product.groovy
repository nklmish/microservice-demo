package com.nklmish.cs.catalogs.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties
class Product {
    int id
    String name
    String description
    List<Comment> comments
    Price price
}
