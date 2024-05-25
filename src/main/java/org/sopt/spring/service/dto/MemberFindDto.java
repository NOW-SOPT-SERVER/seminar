package org.sopt.spring.service.dto;

import org.sopt.spring.domain.Member;
import org.sopt.spring.domain.Part;

public record MemberFindDto(
        String name,
        Part part,
        int age
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(member.getName(), member.getPart(), member.getAge());
    }
}
