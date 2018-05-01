package com.power.assistant.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 29
 */
@Getter
@Setter
public class OrgVo {
    private long id;
    private String fullname;
    private String shortname;
    private long pid;
    private String code;
    private String addressDetail;
    private String phone;
    private long managerId;
    private String detail;
    private long status;
    private String parentName;
    private String managerName;

}
