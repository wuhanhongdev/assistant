package com.power.assistant.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_role")
@Getter
@Setter
public class Role {

  private Long id;
  private String name;
  private long status;
  private long canDelete;
}
