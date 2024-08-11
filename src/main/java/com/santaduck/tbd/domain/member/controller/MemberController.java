package com.santaduck.tbd.domain.member.controller;

import com.santaduck.tbd.domain.member.dto.MemberRequest;
import com.santaduck.tbd.domain.member.service.MemberService;
import com.santaduck.tbd.global.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @Operation(summary = "회원가입")
    public BaseResponse<Void> addMember(@Valid @RequestBody MemberRequest request) {
        log.info(">> 회원가입 요청 : {}", request.toString());
        memberService.join(request);
        return new BaseResponse<>("0", "회원가입 성공", null);
    }
}
