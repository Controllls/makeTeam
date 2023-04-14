package com.p2AE.pdf.repository;

import com.p2AE.pdf.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    public void testMember(){
        Member member = new Member();
        member.setNickName("abc");
        memberRepository.save(member);

        Member findMember = memberRepository.findOne(1L);


        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getNickName()).isEqualTo(member.getNickName());
        assertThat(findMember).isEqualTo(member);


    }

}