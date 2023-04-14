package com.p2AE.pdf.api;

import com.p2AE.pdf.domain.Member;
import com.p2AE.pdf.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberApi {
    private final MemberService memberService;

    @PostMapping("/tokensignin")
    public String getToken(String token){
        log.info(token);
        return token ;
    }

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody Member member){ //RequestBody - Json으로 들어온거 member로 쫙
        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private final Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
