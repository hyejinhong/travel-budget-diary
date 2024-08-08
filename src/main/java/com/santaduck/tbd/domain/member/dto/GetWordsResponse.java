package com.santaduck.tbd.domain.member.dto;

import com.santaduck.tbd.domain.member.model.WordType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GetWordsResponse {
    private List<String> words;
    private WordType type;
}
