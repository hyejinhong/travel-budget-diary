package com.santaduck.tbd.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@ToString(exclude = "password")
@NoArgsConstructor
public class MemberRequest {
    private String nickname;
    private String password;
    private String email;
}
