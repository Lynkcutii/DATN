package org.example.module_banquanao.Exception;

import java.util.List;
import java.util.Map;

public class ValidationException extends RuntimeException {
    private List<Map<String, String>> errors;

    public ValidationException(List<Map<String, String>> errors) {
        super("Validation Failed");
        this.errors = errors;
    }

    public List<Map<String, String>> getErrors() {
        return errors;
    }
}
