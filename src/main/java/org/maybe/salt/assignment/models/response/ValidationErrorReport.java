package org.maybe.salt.assignment.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Value;
import org.maybe.salt.assignment.validation.type.TypeValidationError;

import java.util.Collection;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorReport {
    JsonNode value;
    Collection<TypeValidationError> errors;
}
