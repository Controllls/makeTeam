package com.p2AE.pdf.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;



    private String email;

    private String access_token; // session

    private String nickName;

    private String refresh_token;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Board> boardList;

//    protected Member(){
//
//    }
//
//    public Member(String email , String access_token , String nickName, String refresh_token){
//
//    }

}