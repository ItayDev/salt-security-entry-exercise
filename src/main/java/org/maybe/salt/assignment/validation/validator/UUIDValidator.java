package org.maybe.salt.assignment.validation.validator;

import org.maybe.salt.assignment.models.FieldType;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UUIDValidator extends RegexValidator {
    public UUIDValidator() {
        super(Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"));
    }

    @Override
    protected String getErrorCause() {
        return "Not a valid UUID value";
    }

    @Override
    public FieldType getValidationType() {
        return FieldType.UUID;
    }
}
