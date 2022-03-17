package br.com.siecola.aws_project01.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super(String.format("Product of id: %s not found", id));
    }
}
