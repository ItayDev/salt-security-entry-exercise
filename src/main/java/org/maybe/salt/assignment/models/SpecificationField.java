package org.maybe.salt.assignment.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecificationField extends Field {
    private Collection<FieldType> types;
    private boolean required;
}
