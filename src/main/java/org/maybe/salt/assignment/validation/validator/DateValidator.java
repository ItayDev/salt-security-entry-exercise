package org.maybe.salt.assignment.validation.validator;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.validator.GenericValidator;
import org.maybe.salt.assignment.models.FieldType;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class DateValidator implements TypeValidator {
    private final String dateFormat = "dd-MM-yyyy";

    @Override
    public Optional<TypeValidationError> validate(JsonNode fieldValue) {
        if(GenericValidator.isDate(fieldValue.asText(), this.dateFormat, true)) {
            return Optional.empty();
        }

        return Optional.of(new TypeValidationError("Not a valid date"));
    }

    @Override
    public FieldType getValidationType() {
        return FieldType.DATE;
    }
}
