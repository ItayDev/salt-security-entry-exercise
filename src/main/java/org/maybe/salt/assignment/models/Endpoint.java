package org.maybe.salt.assignment.models;

import org.springframework.http.HttpMethod;

import java.util.Collection;

public class Endpoint<T extends Field> {
    private String path;
    private HttpMethod method;
    private Collection<T> queryParameters;
    private Collection<T> headers;
    private Collection<T> body;
}
