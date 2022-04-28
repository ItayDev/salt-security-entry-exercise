package org.maybe.salt.assignment.validation;

import lombok.RequiredArgsConstructor;
import org.maybe.salt.assignment.models.FieldType;
import org.maybe.salt.assignment.validation.validator.TypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValidationsConfiguration {
    List<TypeValidator> validators;

    @Bean
    Map<FieldType, List<TypeValidator>> getValidators() {
        return validators.stream().collect(Collectors.groupingBy(TypeValidator::getValidationType));
    }
}
