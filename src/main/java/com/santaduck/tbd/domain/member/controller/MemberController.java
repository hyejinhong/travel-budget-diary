package com.santaduck.tbd.domain.member.controller;

import com.santaduck.tbd.domain.member.dto.MemberRequest;
import com.santaduck.tbd.domain.member.entity.Member;
import com.santaduck.tbd.domain.member.repository.MemberRepository;
import com.santaduck.tbd.domain.member.service.MemberService;
import com.santaduck.tbd.global.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/api/member")
    @Operation(summary = "회원가입")
    public BaseResponse<Void> addMember(@Valid @RequestBody MemberRequest request) {
        memberService.join(request);
        return new BaseResponse<>("0", "회원가입 성공", null);
    }
}
