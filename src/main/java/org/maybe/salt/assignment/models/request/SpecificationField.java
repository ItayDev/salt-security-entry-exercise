package org.maybe.salt.assignment.models.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.maybe.salt.assignment.models.FieldType;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class SpecificationField extends Field {
    private Collection<FieldType> types;
    private boolean required;
}
