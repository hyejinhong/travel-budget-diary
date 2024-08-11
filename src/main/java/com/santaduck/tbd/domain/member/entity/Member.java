package com.santaduck.tbd.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20, nullable = false)
    private String memberId;
    @Column(length = 16, nullable = false)
    private String nickname;
    @Column(length = 256, nullable = false)
    private String password;
    @Column(length = 20, nullable = false)
    private String email;

    private Timestamp generatedAt;
    private Timestamp modifiedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
}
