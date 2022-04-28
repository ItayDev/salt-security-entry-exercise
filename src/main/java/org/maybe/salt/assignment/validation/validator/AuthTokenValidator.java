package org.maybe.salt.assignment.validation.validator;

import org.maybe.salt.assignment.models.FieldType;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AuthTokenValidator extends RegexValidator {
    public AuthTokenValidator(Pattern regex) {
        super(Pattern.compile("^Bearer\\s[a-zA-Z0-9]*$"));
    }

    @Override
    protected String getErrorCause() {
        return "Not an auth token";
    }

    @Override
    public FieldType getValidationType() {
        return FieldType.AUTH_TOKEN;
    }
}
