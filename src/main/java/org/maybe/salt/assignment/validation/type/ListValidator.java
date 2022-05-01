package org.maybe.salt.assignment.validation.type;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.maybe.salt.assignment.models.FieldType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ListValidator implements TypeValidator {
    @Override
    public Optional<TypeValidationError> validate(JsonNode fieldValue) {
        if(fieldValue instanceof ArrayNode) {
            return Optional.empty();
        }
         return Optional.of(new TypeValidationError("Not a list"));
    }

    @Override
    public FieldType getValidationType() {
        return FieldType.LIST;
    }
}
