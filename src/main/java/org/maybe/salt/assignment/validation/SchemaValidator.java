package org.maybe.salt.assignment.validation;

import lombok.RequiredArgsConstructor;
import org.maybe.salt.assignment.models.EndpointClassificationSchema;
import org.maybe.salt.assignment.models.EndpointClassificationSchema.ValidationFields;
import org.maybe.salt.assignment.models.FieldType;
import org.maybe.salt.assignment.models.request.*;
import org.maybe.salt.assignment.models.response.ValidationErrorReport;
import org.maybe.salt.assignment.models.response.ValidationResponse;
import org.maybe.salt.assignment.validation.validator.TypeValidationError;
import org.maybe.salt.assignment.validation.validator.TypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.maybe.salt.assignment.utils.CollectionUtils.groupBy;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchemaValidator {
    private final Map<FieldType, List<TypeValidator>> validationFactory;

    public ValidationResponse validateEndpoint(RequestEndpoint requestEndpoint, EndpointClassificationSchema modelEndpoint) {
        return ValidationResponse.builder()
                .path(requestEndpoint.getPath())
                .method(requestEndpoint.getMethod())
                .queryParams(this.validateFields(
                        groupBy(requestEndpoint.getQueryParams().stream(), ConcreteField::getName),
                        modelEndpoint.getQueryParams())
                ).headers(this.validateFields(
                        groupBy(requestEndpoint.getHeaders().stream(), ConcreteField::getName),
                        modelEndpoint.getHeaders())
                ).body(this.validateFields(
                        groupBy(requestEndpoint.getBody().stream(), ConcreteField::getName),
                        modelEndpoint.getBody())
                ).build();
    }

    private Map<String, ValidationErrorReport> validateFields(Map<String, ConcreteField> fieldsToValidate, ValidationFields validationFields) {
        Map<String, ValidationErrorReport> errors = this.runValidationOnFields(fieldsToValidate.values().stream(), validationFields);

        this.validateRequiredFields(validationFields.getRequiredFields(), fieldsToValidate, errors);

        return errors;
    }

    private Map<String, ValidationErrorReport> runValidationOnFields(Stream<ConcreteField> fieldsToValidate, ValidationFields validationFields) {
        return fieldsToValidate.map(field -> {
            SpecificationField specificationField = validationFields.getFields().get(field.getName());
            if (specificationField == null) {
                return Map.entry(field.getName(), ValidationErrorReport.builder().
                        errors(Collections.singletonList(new TypeValidationError("Field not in schema")))
                        .build()
                );
            } else {
                List<TypeValidationError> fieldErrors = specificationField.getTypes().stream()
                        .flatMap(type -> this.validationFactory.get(type).stream())
                        .map(validation -> validation.validate(field.getValue()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());

                if (fieldErrors.size() > 0) {
                    return Map.entry(field.getName(), ValidationErrorReport.builder()
                            .value(field.getValue())
                            .errors(fieldErrors)
                            .build()
                    );
                }

                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void validateRequiredFields(Set<String> requiredFields, Map<String, ConcreteField> fieldsToValidate, Map<String, ValidationErrorReport> errors) {
        requiredFields.forEach(requiredField -> {
            if(fieldsToValidate.get(requiredField) == null) {
                errors.put(requiredField, ValidationErrorReport.builder()
                        .errors(Collections.singletonList(new TypeValidationError("This field is required")))
                        .build()
                );
            }
        });
    }
}
