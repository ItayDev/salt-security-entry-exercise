package org.maybe.salt.assignment.validation.validator;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public abstract class RegexValidator implements TypeValidator {
    protected final Pattern regex;

    protected abstract String getErrorCause();

    @Override
    public Optional<Collection<TypeValidationError>> validate(JsonNode fieldValue) {
        String fieldStringValue = fieldValue.asText();
        if (this.satisfiesPattern(fieldStringValue)) {
            return Optional.empty();
        }

        return Optional.of(Collections.singletonList(new TypeValidationError(this.getErrorCause())));
    }

    private boolean satisfiesPattern(String value) {
        return this.regex.matcher(value).matches();
    }
}
