package org.maybe.salt.assignment.validation.validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import org.maybe.salt.assignment.models.FieldType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BooleanValidator implements TypeValidator {
    @Override
    public Optional<TypeValidationError> validate(JsonNode fieldValue) {
        if(fieldValue instanceof BooleanNode) {
            return Optional.empty();
        }
        return Optional.of(new TypeValidationError("Not a boolean"));
    }

    @Override
    public FieldType getValidationType() {
        return FieldType.BOOLEAN;
    }
}
