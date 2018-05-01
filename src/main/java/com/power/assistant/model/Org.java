package com.power.assistant.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_org")
@Getter
@Setter
public class Org {

  private Long id;
  private String fullname;
  private String shortname;
  private Long pid;
  private String code;
  private String addressDetail;
  private String phone;
  private Long managerId;
  private String detail;
  private Long status;
  private Long canDelete;

}
