package com.power.assistant.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
@Getter
@Setter
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String cardNo;
  private String certNo;
  private String loginname;
  private String password;
  private Long gender;
  private String photosrc;
  private String homeDetail;
  private String phone;
  private String mobile;
  private Long orgId;
  private Integer status;

}
