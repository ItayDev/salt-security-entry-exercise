package org.maybe.salt.assignment.models.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConcreteField extends Field {
    private JsonNode value;
}
