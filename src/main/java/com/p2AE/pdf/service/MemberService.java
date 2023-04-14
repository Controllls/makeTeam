package com.p2AE.pdf.service;

import com.p2AE.pdf.domain.Member;
import com.p2AE.pdf.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final만 생성자 만들어줌 롬복
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional  //따로하면 우선권 readonly false
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId(); //em이 Id가져와서
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


    //todo nickName update
    @Transactional
    public void update(String email , String nickName) {
        List<Member> byEmail = memberRepository.findByEmail(email);
        byEmail.get(0).setNickName(nickName);
    }

    public List<Member> findByEmail(String email){
            List<Member> byEmail = memberRepository.findByEmail(email);
//        if (byEmail != null){
            return byEmail;
//        }else{
//            throw new IOException("멤버없음");
//        }
    }




    public List<Member> findByAccessToken(String access_token){
        List<Member> byEmail = memberRepository.findByAccessToken(access_token);
        return byEmail;
    }

}
