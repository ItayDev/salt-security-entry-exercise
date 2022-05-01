package org.maybe.salt.assignment.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpMethod;

import java.util.Map;

import static org.maybe.salt.assignment.utils.CollectionUtils.isMapEmpty;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidationResponse {
    String path;
    HttpMethod method;
    Map<String, ValidationErrorReport> queryParams;
    Map<String, ValidationErrorReport> headers;
    Map<String, ValidationErrorReport> body;
    @Builder.Default
    boolean schemaExists = true;

    public StatusType getStatus() {
        if (isMapEmpty(this.queryParams) && isMapEmpty(this.headers) && isMapEmpty(this.body) && schemaExists) {
            return StatusType.OK;
        }

        return StatusType.ERROR;
    }
}
