package com.nklmish.cs.comments.model;

public class Comment {
    private final int id;
    private final int productId;
    private final String author;
    private final String content;

    public Comment(int id, int productId, String author, String content) {
        this.id = id;
        this.productId = productId;
        this.author = author;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", productId=" + productId +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
