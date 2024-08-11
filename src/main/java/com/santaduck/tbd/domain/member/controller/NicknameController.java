package com.santaduck.tbd.domain.member.controller;

import com.santaduck.tbd.domain.member.dto.CreateWordRequest;
import com.santaduck.tbd.domain.member.dto.GenerateNicknameResponse;
import com.santaduck.tbd.domain.member.dto.GetWordsResponse;
import com.santaduck.tbd.domain.member.model.WordType;
import com.santaduck.tbd.domain.member.service.NicknameService;
import com.santaduck.tbd.global.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/nickname")
@RequiredArgsConstructor
public class NicknameController {

    private final NicknameService nicknameService;

    @GetMapping
    public BaseResponse<GenerateNicknameResponse> generateNickname() {
        log.info("### 닉네임 생성 요청");
        return new BaseResponse<>("00", "닉네임이 생성되었습니다.", nicknameService.generateNickname());
    }

    @GetMapping("/words")
    public BaseResponse<GetWordsResponse> getWords(@RequestParam WordType wordType) {
      log.info("### {} 단어 리스트 조회", wordType);
      return new BaseResponse<>("00", "조회 성공", nicknameService.getWords(wordType));
    }

    @PostMapping("/words")
    public BaseResponse<Void> createWord(@RequestBody CreateWordRequest request) {
        log.info("### 단어 생성 요청 | {}", request.toString());
        nicknameService.insertWord(request);
        return new BaseResponse<>("00", "생성되었습니다.", null);
    }
}
