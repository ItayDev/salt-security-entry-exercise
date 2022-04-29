package org.maybe.salt.assignment.models.response;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Value;
import org.maybe.salt.assignment.validation.validator.TypeValidationError;

import java.util.Collection;

@Value
@Builder
public class ValidationErrorReport {
    JsonNode value;
    Collection<TypeValidationError> errors;
}
