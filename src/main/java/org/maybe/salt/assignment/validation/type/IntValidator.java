package org.maybe.salt.assignment.validation.type;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import org.maybe.salt.assignment.models.FieldType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntValidator implements TypeValidator{
    @Override
    public Optional<TypeValidationError> validate(JsonNode fieldValue) {
        if(fieldValue instanceof IntNode) {
            return Optional.empty();
        }

        return Optional.of(new TypeValidationError("Not an integer"));
    }

    @Override
    public FieldType getValidationType() {
        return FieldType.INT;
    }
}
