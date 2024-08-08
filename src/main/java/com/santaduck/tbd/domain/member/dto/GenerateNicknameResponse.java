package com.santaduck.tbd.domain.member.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenerateNicknameResponse {
    private String nickname;
}
