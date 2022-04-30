package org.maybe.salt.assignment.models;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.maybe.salt.assignment.models.request.Field;
import org.maybe.salt.assignment.models.request.ModelEndpoint;
import org.maybe.salt.assignment.models.request.SpecificationField;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.maybe.salt.assignment.utils.CollectionUtils.groupBy;

@Value
public class EndpointClassificationSchema {
    ValidationFields queryParams;
    ValidationFields headers;
    ValidationFields body;
    public EndpointClassificationSchema(ModelEndpoint modelEndpoint) {
        this.queryParams = new ValidationFields(groupBy(modelEndpoint.getQueryParams().stream(), Field::getName), getRequiredFields(modelEndpoint.getQueryParams()));
        this.headers = new ValidationFields(groupBy(modelEndpoint.getHeaders().stream(), Field::getName), getRequiredFields(modelEndpoint.getHeaders()));
        this.body = new ValidationFields(groupBy(modelEndpoint.getBody().stream(), Field::getName), getRequiredFields(modelEndpoint.getBody()));
    }

    private static Set<String> getRequiredFields(Collection<SpecificationField> fields) {
        return fields.stream().filter(SpecificationField::isRequired).map(SpecificationField::getName).collect(Collectors.toSet());
    }

    @Value
    @RequiredArgsConstructor
    public static class ValidationFields {
        Map<String, SpecificationField> fields;
        Set<String> requiredFields;
    }
}
