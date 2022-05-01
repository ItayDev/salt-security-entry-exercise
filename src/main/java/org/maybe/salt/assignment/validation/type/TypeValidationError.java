package org.maybe.salt.assignment.validation.type;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class TypeValidationError {
    String cause;
}
