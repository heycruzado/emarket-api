package com.alex.emarket_api.dto;

import java.util.List;

public record GenericResponse <T> (
        int status,
        String message,
        List<T> data
) {
}
