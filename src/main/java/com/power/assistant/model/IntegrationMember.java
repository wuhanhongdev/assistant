package com.power.assistant.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@Table(name = "t_integration_member")
public class IntegrationMember {

  private Long id;
  private Long memberId;
  private long score;
  private String content;
  private String yearNo;
  @Transient
  private String memberName;
}
