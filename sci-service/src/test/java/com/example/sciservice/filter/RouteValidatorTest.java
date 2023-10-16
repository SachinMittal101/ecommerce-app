package com.example.sciservice.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RouteValidatorTest {

    private RouteValidator routeValidator;

    @BeforeEach
    public void setUp() {
        routeValidator = new RouteValidator();
    }

    @Test
    public void testIsSecuredWithSecuredRoute() {
        ServerHttpRequest request = createServerRequest("/secure");
        boolean result = routeValidator.isSecured.test(request);
        assertTrue(result);
    }

    @Test
    public void testIsSecuredWithOpenApiRoute() {
        ServerHttpRequest request = createServerRequest("/auth/token");
        boolean result = routeValidator.isSecured.test(request);
        assertFalse(result);
    }

    @Test
    public void testIsSecuredWithEurekaRoute() {
        ServerHttpRequest request = createServerRequest("/eureka/instance");
        boolean result = routeValidator.isSecured.test(request);
        assertFalse(result);
    }

    private ServerHttpRequest createServerRequest(String path) {
        return new TestServerHttpRequest(path);
    }

    private static class TestServerHttpRequest implements ServerHttpRequest {
        private String path;

        public TestServerHttpRequest(String path) {
            this.path = path;
        }

        @Override
        public HttpMethod getMethod() {
            return null;
        }

        @Override
        public URI getURI() {
            return URI.create("http://example.com" + path);
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public RequestPath getPath() {
            return null;
        }

        @Override
        public MultiValueMap<String, String> getQueryParams() {
            return null;
        }

        @Override
        public MultiValueMap<String, HttpCookie> getCookies() {
            return null;
        }

        @Override
        public Flux<DataBuffer> getBody() {
            return null;
        }

        @Override
        public HttpHeaders getHeaders() {
            return null;
        }
    }
}
