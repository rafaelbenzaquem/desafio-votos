package br.com.benzaquem.desafiovotos.util;

import feign.FeignException;
import feign.Request;

import java.util.Collection;
import java.util.Map;

public class FeignExceptionForTests extends FeignException {
    public FeignExceptionForTests(int status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public FeignExceptionForTests(int status, String message, Throwable cause, byte[] responseBody, Map<String, Collection<String>> responseHeaders) {
        super(status, message, cause, responseBody, responseHeaders);
    }

    public FeignExceptionForTests(int status, String message) {
        super(status, message);
    }

    public FeignExceptionForTests(int status, String message, byte[] responseBody, Map<String, Collection<String>> responseHeaders) {
        super(status, message, responseBody, responseHeaders);
    }

    public FeignExceptionForTests(int status, String message, Request request, Throwable cause) {
        super(status, message, request, cause);
    }

    public FeignExceptionForTests(int status, String message, Request request, Throwable cause, byte[] responseBody, Map<String, Collection<String>> responseHeaders) {
        super(status, message, request, cause, responseBody, responseHeaders);
    }

    public FeignExceptionForTests(int status, String message, Request request) {
        super(status, message, request);
    }

    public FeignExceptionForTests(int status, String message, Request request, byte[] responseBody, Map<String, Collection<String>> responseHeaders) {
        super(status, message, request, responseBody, responseHeaders);
    }
}
