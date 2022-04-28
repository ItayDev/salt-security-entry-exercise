package org.maybe.salt.assignment.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FieldType {
    INT("int"),
    STRING("String"),
    BOOLEAN("Boolean"),
    LIST("List"),
    DATE("Date"),
    EMAIL("Email"),
    UUID("UUID"),
    AUTH_TOKEN("Auth-Token");
    private final String value;
}
