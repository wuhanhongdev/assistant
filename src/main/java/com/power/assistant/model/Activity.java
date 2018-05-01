package com.power.assistant.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_activity")
public class Activity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String yearNo;
  private String period;
  private Long orgId;
  private String content;
  private String name;
  @Transient
  private String orgName;
}
