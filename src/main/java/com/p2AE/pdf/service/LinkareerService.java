package com.p2AE.pdf.service;

import com.p2AE.pdf.domain.Linkareer;
import com.p2AE.pdf.domain.Member;
import com.p2AE.pdf.repository.LinkareerRepository;
import com.p2AE.pdf.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final만 생성자 만들어줌 롬복
public class LinkareerService {

    private final LinkareerRepository linkareerRepository;

    @Transactional  //따로하면 우선권 readonly false
    public Long join(Linkareer linkareer){
        linkareerRepository.save(linkareer);

        return linkareer.getId();
    }


}
