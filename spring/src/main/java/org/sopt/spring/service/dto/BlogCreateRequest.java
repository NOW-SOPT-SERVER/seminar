package org.sopt.spring.service.dto;

public record BlogCreateRequest(
        String title,
        String description
) {
}
