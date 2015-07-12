package com.nklmish.cs.comments.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.Immutable

@Immutable
@JsonIgnoreProperties
class Comment {
    int id
    int productId
    String author
    String content
}
