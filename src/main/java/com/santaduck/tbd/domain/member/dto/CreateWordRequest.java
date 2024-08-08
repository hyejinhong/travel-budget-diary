package com.santaduck.tbd.domain.member.dto;

import com.santaduck.tbd.domain.member.model.WordType;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateWordRequest {
    private String word;
    private WordType type;
}
