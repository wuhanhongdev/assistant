package com.power.assistant.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 30
 */
@Getter
@Setter
public class UserVo {
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
    private String orgName;
    private Integer status;
    private boolean checked;
}
