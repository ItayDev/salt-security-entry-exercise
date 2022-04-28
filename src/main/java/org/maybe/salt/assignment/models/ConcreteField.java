package org.maybe.salt.assignment.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConcreteField extends Field {
    private String value;
}
