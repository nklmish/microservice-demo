package com.nklmish.cs.comments.boundary;

import com.google.common.collect.Lists;
import com.nklmish.cs.comments.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/comment")
public class CommentService {

    private static final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    @RequestMapping(value = "/{productId}", produces = APPLICATION_JSON_VALUE)
        List<Comment> findComments(@PathVariable Integer productId) {
        LOG.debug("retrieving comments for product {}", productId);
        return Lists.newArrayList(
                new Comment(1, productId, "John", "I like this product very much!!!"),
                new Comment(2, productId, "Bob", "A must buy, worth the price")
        );
    }
}
