package org.maybe.salt.assignment.controllers;

import org.maybe.salt.assignment.models.ModelEndpoint;
import org.maybe.salt.assignment.models.RequestEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointValidationController {
    @PostMapping("/endpoint/model")
    public void registerModel(@RequestBody ModelEndpoint modelEndpoint) {

    }

    @PostMapping("/endpoint/validate")
    public void validate(@RequestBody RequestEndpoint modelEndpoint) {
        System.out.println("got input");
    }
}
