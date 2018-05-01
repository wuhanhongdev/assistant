package com.power.assistant.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "t_integration")
public class Integration {

  private long id;
  private String content;
  private String yearText;
  private String monthText;

}
