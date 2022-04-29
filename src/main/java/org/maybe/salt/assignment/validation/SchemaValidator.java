package org.maybe.salt.assignment.validation;

import lombok.RequiredArgsConstructor;
import org.maybe.salt.assignment.models.FieldType;
import org.maybe.salt.assignment.models.request.*;
import org.maybe.salt.assignment.models.response.ValidationErrorReport;
import org.maybe.salt.assignment.models.response.ValidationResponse;
import org.maybe.salt.assignment.validation.validator.TypeValidationError;
import org.maybe.salt.assignment.validation.validator.TypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchemaValidator {
    private final Map<FieldType, List<TypeValidator>> validations;

    public ValidationResponse validateEndpoint(RequestEndpoint requestEndpoint, ModelEndpoint modelEndpoint) {
        return ValidationResponse.builder()
                .path(requestEndpoint.getPath())
                .method(requestEndpoint.getMethod())
                .queryParams(this.validateFields(this.convertFieldsToMap(requestEndpoint.getQueryParams()), this.convertFieldsToMap(modelEndpoint.getQueryParams())))
                .headers(validateFields(this.convertFieldsToMap(requestEndpoint.getHeaders()), this.convertFieldsToMap(modelEndpoint.getHeaders())))
                .body(this.validateFields(this.convertFieldsToMap(requestEndpoint.getBody()), this.convertFieldsToMap(modelEndpoint.getBody())))
                .build();
    }

    private <T extends Field> Map<String, T> convertFieldsToMap(Collection<T> fields) {
        return fields.stream().collect(Collectors.toMap(Field::getName, Function.identity()));
    }

    private Map<String, ValidationErrorReport> validateFields(Map<String, ConcreteField> fieldsToValidate, Map<String, SpecificationField> validationFields) {
        Map<String, ValidationErrorReport> errors = new HashMap<>();

        fieldsToValidate.values().forEach(field -> {
            SpecificationField specificationField = validationFields.get(field.getName());
            if (specificationField == null) {
                errors.put(field.getName(), ValidationErrorReport.builder().
                        errors(Collections.singletonList(new TypeValidationError("Field not in schema")))
                        .build()
                );
            } else {
                List<TypeValidationError> fieldErrors = specificationField.getTypes().stream()
                        .flatMap(type -> this.validations.get(type).stream())
                        .map(validation -> validation.validate(field.getValue()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());

                if (fieldErrors.size() > 0) {
                    errors.put(field.getName(), ValidationErrorReport.builder()
                            .value(field.getValue())
                            .errors(fieldErrors)
                            .build()
                    );
                }
            }
        });

        validationFields.values().stream().filter(SpecificationField::isRequired).forEach(requiredField -> {
            if (fieldsToValidate.get(requiredField.getName()) == null) {
                errors.put(requiredField.getName(), ValidationErrorReport.builder()
                        .errors(Collections.singletonList(new TypeValidationError("This field is required")))
                        .build()
                );
            }
        });
        return errors;
    }
}
