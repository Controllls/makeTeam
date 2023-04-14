package com.p2AE.pdf.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Linkareer {

    @Id
    @GeneratedValue
    @Column(name = "linkareer_id")
    private Long id;

    private String imageUrl;
    private String name;
    private String type;
    private String target;
    private String scale;
    private String period;
    private String homePageUrl;
    private String benefit;
    private String field;
    private String extraBenefit;
}
