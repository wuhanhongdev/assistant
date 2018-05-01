package com.power.assistant.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "t_member")
public class Member {

  private Long id;
  private String name;
  private String birthday;
  private String degree;
  private String inTime;
  private long isManager;
  private long integration;
  private String level;
  private String starMember;
  private String starManager;
  private String memberCreate;
  private String promise;
  private String photosrc;
  private Long orgId;
  private Integer pass;
  private String qrcode;

}
