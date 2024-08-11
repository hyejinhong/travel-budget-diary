package com.santaduck.tbd.domain.member.service;

import com.santaduck.tbd.domain.member.dto.MemberDetail;
import com.santaduck.tbd.domain.member.entity.Member;
import com.santaduck.tbd.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByNickname(username);
        if (member.isEmpty())
            throw new UsernameNotFoundException("해당하는 회원정보를 찾을 수 없습니다.");
        return new MemberDetail(member.get());
    }
}
