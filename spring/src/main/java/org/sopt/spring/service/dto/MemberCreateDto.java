package org.sopt.spring.service.dto;

import org.sopt.spring.domain.Part;

public record MemberCreateDto(
        String name,
        Part part,
        int age
) {
}
