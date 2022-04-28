package org.maybe.salt.assignment.validation.validator;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class TypeValidationError {
    String cause;
}
