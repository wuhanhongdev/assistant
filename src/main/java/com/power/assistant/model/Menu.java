package com.power.assistant.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_menu")
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Long pid;

    private String name;

    private String icon;

    private String url;

    private Integer num;

    private Integer ismenu;

    private String tips;

    private Integer status;

    private Integer isopen;

}