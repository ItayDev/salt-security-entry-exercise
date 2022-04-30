package org.maybe.salt.assignment.persistence;

import org.maybe.salt.assignment.models.EndpointClassificationSchema;
import org.maybe.salt.assignment.models.request.ModelEndpoint;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// TODO: For the exercise I keep everything in memory, on production I would probably store it in a non sql
//  (there are no joins and it is much simpler saving objects) to save my models. Also, depending on the load
//  on the database / wanted response times a cache server such as Redis could be used as well.
@Service
public class LearnedModelRegistry {
    private final Map<String, EndpointClassificationSchema> savedModels = new ConcurrentHashMap<>();

    public void register(ModelEndpoint endpoint) {
        savedModels.put(formatEndpointName(endpoint.getPath(), endpoint.getMethod()), new EndpointClassificationSchema(endpoint));
    }

    public Optional<EndpointClassificationSchema> get(String endpointPath, HttpMethod method) {
        return Optional.ofNullable(savedModels.get(formatEndpointName(endpointPath, method)));
    }

    private static String formatEndpointName(String endpointPath, HttpMethod method) {
        return String.format("%s-%s", endpointPath, method);
    }
}
