package org.maybe.salt.assignment.validation.validator;

import org.maybe.salt.assignment.models.FieldType;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator extends RegexValidator{
    public EmailValidator() {
        // Apparently this is an efficient and correct pattern as explained here (the accepted answer): https://stackoverflow.com/questions/201323/how-can-i-validate-an-email-address-using-a-regular-expression
        super(Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"));
    }

    @Override
    protected String getErrorCause() {
        return "Not an email address";
    }

    @Override
    public FieldType getValidationType() {
        return FieldType.EMAIL;
    }
}
