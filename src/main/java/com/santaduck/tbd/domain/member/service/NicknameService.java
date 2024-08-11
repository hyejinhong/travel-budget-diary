package com.santaduck.tbd.domain.member.service;

import com.santaduck.tbd.domain.member.dto.CreateWordRequest;
import com.santaduck.tbd.domain.member.dto.GenerateNicknameResponse;
import com.santaduck.tbd.domain.member.dto.GetWordsResponse;
import com.santaduck.tbd.domain.member.entity.Word;
import com.santaduck.tbd.domain.member.model.WordType;
import com.santaduck.tbd.domain.member.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NicknameService {

    private final WordRepository wordRepository;

    /**
     * 단어 타입에 따른 단어 리스트 조회
     */
    public GetWordsResponse getWords(WordType wordType) {
        List<Word> words = wordRepository.findByType(wordType);
        List<String> wordStrs = new ArrayList<>();
        for (Word word : words) {
            wordStrs.add(word.getWord());
        }
        return GetWordsResponse.builder()
                .words(wordStrs)
                .type(wordType)
                .build();
    }

    /**
     * 단어 생성
     */
    public void insertWord(CreateWordRequest request) {
        Word word = Word.builder()
                .word(request.getWord())
                .type(request.getType())
                .build();

        wordRepository.save(word);
    }

    /**
     * 단어 조합하여 랜덤 닉네임 생성
     */
    public GenerateNicknameResponse generateNickname() {
        String first = wordRepository.findRandomWordByType(WordType.FIRST);
        String second = wordRepository.findRandomWordByType(WordType.SECOND);

        log.info("> First word : {}", first);
        log.info("> Second word : {}", second);

        return GenerateNicknameResponse.builder()
                .nickname(first + " " + second)
                .build();
    }
}
