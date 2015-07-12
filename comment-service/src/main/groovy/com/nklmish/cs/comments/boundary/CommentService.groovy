package com.nklmish.cs.comments.boundary

import com.nklmish.cs.comments.model.Comment
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@RequestMapping("/comment")
class CommentService {

    @RequestMapping(value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    def findComments(@PathVariable Integer productId) {
        [
                new Comment(1, productId, "John", "I like this product very much!!!"),
                new Comment(2, productId, "Bob", "A must buy, worth the price")
        ]
    }
}
