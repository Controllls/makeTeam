package com.p2AE.pdf.repository;

import com.p2AE.pdf.domain.Linkareer;
import com.p2AE.pdf.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LinkareerRepository {

    private final EntityManager em; //스프링부트가 conext해

    public void save(Linkareer linkareer){
        if(linkareer.getId() == null){

            em.persist(linkareer);
        }else{
            em.merge(linkareer);
        }
    }

}
