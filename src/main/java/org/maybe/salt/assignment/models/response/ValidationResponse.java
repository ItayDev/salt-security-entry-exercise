package org.maybe.salt.assignment.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.springframework.http.HttpMethod;

import java.util.Map;

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

    public String getStatus() {
        if (isMapEmpty(this.queryParams) && isMapEmpty(this.headers) && isMapEmpty(this.body) && schemaExists) {
            return "OK";
        }

        return "ERROR";
    }

    private static <k, v> boolean isMapEmpty(Map<k, v> map) {
        return map == null || map.isEmpty();
    }
}
