package org.maybe.salt.assignment.validation.validator;

import com.fasterxml.jackson.databind.JsonNode;
import org.maybe.salt.assignment.models.FieldType;

import java.util.Optional;

public interface TypeValidator {
    Optional<TypeValidationError> validate(JsonNode fieldValue);
    FieldType getValidationType();
}
