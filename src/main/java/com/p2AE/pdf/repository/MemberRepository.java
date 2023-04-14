package com.p2AE.pdf.repository;

import com.p2AE.pdf.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em; //스프링부트가 conext해

    public void save(Member member){
        if(member.getId() == null){

            em.persist(member);
        }else{
            em.merge(member);
        }
    }



    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m" , Member.class)
                .getResultList();
    }

    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member m where m.email = :email" , Member.class)
                .setParameter("email" , email)
                .getResultList();
    }
    public List<Member> findByAccessToken(String access_token){
        return em.createQuery("select m from Member m where m.access_token = :access_token" , Member.class)
                .setParameter("access_token" , access_token)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name" , Member.class)
                .setParameter("name" , name)
                .getResultList();
    }
}
