package org.maybe.salt.assignment.models;

import lombok.Data;
import org.springframework.http.HttpMethod;

import java.util.Collection;

@Data
public class Endpoint<T extends Field> {
    private String path;
    private HttpMethod method;
    private Collection<T> queryParams;
    private Collection<T> headers;
    private Collection<T> body;
}
