package com.tangela.domain.provider.support;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.google.common.io.BaseEncoding.base64;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class AuthRestTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRestTemplate.class);

    private static final String ACCEPT_ENCODING = "gzip,deflate";

    private RestTemplate restTemplate;

    @Value("${orientdb.user}")
    private String user;

    @Value("${orientdb.password}")
    private String password;


    public AuthRestTemplate() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new AuthRestErrorHandler());
    }

    private HttpEntity<Object> createEntity(Object request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.set("Accept-Encoding", ACCEPT_ENCODING);
        headers.set("Authorization", "Basic " + base64().encode((user + ":" + password).getBytes()));
        HttpEntity<Object> entity;
        if (request == null) {
            entity = new HttpEntity<>(headers);
        } else {
            entity = new HttpEntity<>(request, headers);
        }
        return entity;
    }

    private HttpEntity<Object> createEntity() {
        return this.createEntity(null);
    }

    public <T> ResponseEntity<T> get(String uri, Class<T> clazz) {
        return restTemplate.exchange(uri, GET, createEntity(), clazz);
    }

    public <T> ResponseEntity<T> post(String uri, Object request, Class<T> clazz) {
        return restTemplate.exchange(uri, POST, createEntity(request), clazz);
    }

    public <T> ResponseEntity<T> post(String uri, Class<T> clazz) {
        return restTemplate.exchange(uri, POST, createEntity(), clazz);
    }

    public <T> ResponseEntity<T> put(String uri, Object request, Class<T> clazz) {
        return restTemplate.exchange(uri, PUT, createEntity(request), clazz);
    }

    public <T> ResponseEntity<T> put(String uri, Class<T> clazz) {
        return restTemplate.exchange(uri, PUT, createEntity(), clazz);
    }

    public <T> ResponseEntity<T> delete(String uri, Class<T> clazz) {
        return restTemplate.exchange(uri, DELETE, createEntity(), clazz);
    }

    class AuthRestErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
            HttpStatus statusCode = clientHttpResponse.getStatusCode();
            return statusCode.series() == HttpStatus.Series.CLIENT_ERROR || statusCode.series() == HttpStatus.Series.SERVER_ERROR;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            LOGGER.error("[handleError] Response error: [{}] [{}]", clientHttpResponse.getStatusCode(),
                    clientHttpResponse.getStatusText());
        }
    }
}
