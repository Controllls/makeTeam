package com.p2AE.pdf.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Advertise {
    @Id
    @GeneratedValue
    @Column(name = "advertise_id")
    private Long id;

    private String projectType;

    private String projectDetail;

    private int MemberNum;


}
