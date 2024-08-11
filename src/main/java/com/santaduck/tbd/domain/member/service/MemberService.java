package com.santaduck.tbd.domain.member.service;

import com.santaduck.tbd.domain.member.dto.MemberRequest;
import com.santaduck.tbd.domain.member.entity.Member;
import com.santaduck.tbd.domain.member.exception.MemberException;
import com.santaduck.tbd.domain.member.exception.MemberExceptionType;
import com.santaduck.tbd.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    public void join(MemberRequest request) {
        Optional<Member> emailCheck = memberRepository.findByEmail(request.getEmail());
        Optional<Member> memberIdCheck = memberRepository.findByMemberId(request.getMemberId());

        if (memberIdCheck.isPresent()) {
            throw new MemberException(MemberExceptionType.DUPLICATE_MEMBER_ID);
        }
        if (emailCheck.isPresent()) {
            throw new MemberException(MemberExceptionType.DUPLICATE_EMAIL);
        }

        long now = System.currentTimeMillis();
        Member member = Member.builder()
                .nickname(request.getNickname())
                .memberId(request.getMemberId())
                .password(encoder.encode(request.getPassword()))
                .email(request.getEmail())
                .generatedAt(new Timestamp(now))
                .modifiedAt(new Timestamp(now))
                .build();

        memberRepository.save(member);

    }
}
