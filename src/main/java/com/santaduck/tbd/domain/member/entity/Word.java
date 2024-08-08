package com.santaduck.tbd.domain.member.entity;

import com.santaduck.tbd.domain.member.model.WordType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String word;
    @Enumerated(EnumType.STRING)
    private WordType type;
}
