
-- auto-generated definition
CREATE TABLE t_menu
(
  id     BIGINT AUTO_INCREMENT
  COMMENT '����id'
    PRIMARY KEY,
  code   VARCHAR(255) NULL
  COMMENT '�˵����',
  pid    BIGINT       NULL
  COMMENT '�˵������',
  name   VARCHAR(255) NULL
  COMMENT '�˵�����',
  icon   VARCHAR(255) NULL
  COMMENT '�˵�ͼ��',
  url    VARCHAR(255) NULL
  COMMENT 'url��ַ',
  num    INT(65)      NULL
  COMMENT '�˵������',
  ismenu INT          NULL
  COMMENT '�Ƿ��ǲ˵���1����  0�����ǣ�',
  tips   VARCHAR(255) NULL
  COMMENT '��ע',
  status INT(65)      NULL
  COMMENT '�˵�״̬ :  1:����   0:������',
  isopen INT          NULL
  COMMENT '�Ƿ��:    1:��   0:����'
)
  COMMENT '�˵���'
  ENGINE = InnoDB;

INSERT INTO assistant.t_menu (id, code, pid, name, icon, url, num, ismenu, tips, status, isopen) VALUES (1, 1000, -1, '党建管理', 'fa fa-columns', NULL, 1, 0, '党建管理', 1, 1);
INSERT INTO assistant.t_menu (id, code, pid, name, icon, url, num, ismenu, tips, status, isopen) VALUES (2, 10001, 1, '党员信息管理', 'fa fa-columns', 'jump?source=partyMembers', 2, 1, '党员信息管理', 1, 1);
INSERT INTO assistant.t_menu (id, code, pid, name, icon, url, num, ismenu, tips, status, isopen) VALUES (3, 10002, 1, '主题活动管理', 'fa fa-columns', 'jump?source=activity', 3, 1, '主题活动管理', 1, 1);
INSERT INTO assistant.t_menu (id, code, pid, name, icon, url, num, ismenu, tips, status, isopen) VALUES (4, 2000, -1, '菜单管理', 'fa fa-columns', NULL, 4, 0, '菜单管理', 1, 1);
INSERT INTO assistant.t_menu (id, code, pid, name, icon, url, num, ismenu, tips, status, isopen) VALUES (5, 20001, 4, '机构管理', 'fa fa-columns', 'jump?source=org', 5, 1, '机构管理', 1, 1);
INSERT INTO assistant.t_menu (id, code, pid, name, icon, url, num, ismenu, tips, status, isopen) VALUES (6, 20002, 4, '菜单管理', 'fa fa-columns', 'jump?source=menu', 6, 1, '菜单管理', 1, 1);
