package org.maybe.salt.assignment.controllers;

import lombok.RequiredArgsConstructor;
import org.maybe.salt.assignment.models.request.ModelEndpoint;
import org.maybe.salt.assignment.models.request.RequestEndpoint;
import org.maybe.salt.assignment.models.response.ValidationResponse;
import org.maybe.salt.assignment.persistence.LearnedModelRegistry;
import org.maybe.salt.assignment.validation.SchemaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EndpointValidationController {
    private final LearnedModelRegistry modelRegistry;
    private final SchemaValidator schemaValidator;
    @PostMapping("/endpoint/model")
    public void registerModel(@RequestBody ModelEndpoint modelEndpoint) {
        this.modelRegistry.register(modelEndpoint);
    }

    @PostMapping("/endpoint/validate")
    public ValidationResponse validate(@RequestBody RequestEndpoint requestEndpoint) {
        Optional<ModelEndpoint> modelEndpoint = this.modelRegistry.get(requestEndpoint.getPath(), requestEndpoint.getMethod());

        if(modelEndpoint.isEmpty()) {
            return ValidationResponse.builder()
                    .path(requestEndpoint.getPath())
                    .method(requestEndpoint.getMethod())
                    .schemaExists(false)
                    .build();
        }

        return this.schemaValidator.validateEndpoint(requestEndpoint, modelEndpoint.get());
    }
}
