package com.power.assistant.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class MenuVo {

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

    private boolean checked;

}