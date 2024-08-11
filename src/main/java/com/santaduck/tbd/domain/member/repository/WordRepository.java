package com.santaduck.tbd.domain.member.repository;

import com.santaduck.tbd.domain.member.entity.Word;
import com.santaduck.tbd.domain.member.model.WordType;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> findByType(WordType type);

    @Query("SELECT w.word FROM Word w WHERE w.type = :type ORDER BY RAND() LIMIT 1")
    String findRandomWordByType(@Param("type") WordType type);
}
