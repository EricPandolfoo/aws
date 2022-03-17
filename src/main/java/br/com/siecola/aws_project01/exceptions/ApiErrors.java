package br.com.siecola.aws_project01.exceptions;


import java.util.List;


public class ApiErrors {


    private final List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public ApiErrors(String mensagemErro) {
        this.errors = List.of(mensagemErro);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
}
