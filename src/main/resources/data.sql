create table t_activity
(
	id bigint auto_increment
		primary key,
	period varchar(32) null comment '期次',
	org_id bigint null comment '机构',
	content mediumtext null comment '主题内容',
	name varchar(256) null comment '主题名称',
	year_no varchar(4) null
)
engine=InnoDB charset=utf8
;

create table t_content
(
	content mediumtext null
)
engine=InnoDB charset=utf8
;

create table t_integration
(
	id bigint auto_increment
		primary key,
	content mediumtext null comment '内容',
	year_text varchar(4) null comment '归属年度',
	month_text varchar(2) null comment '归属月度'
)
engine=InnoDB charset=utf8
;

create table t_integration_member
(
	id bigint auto_increment
		primary key,
	member_id bigint null comment '党员ID',
	score int null comment '分数',
	content text null comment '积分细则文字',
	year_no varchar(8) null
)
engine=InnoDB charset=utf8
;

create table t_member
(
	id bigint auto_increment
		primary key,
	name varchar(128) null comment '姓名',
	birthday varchar(16) null comment '出生年月',
	degree varchar(6) null comment '学历',
	in_time varchar(16) null comment '入党时间',
	is_manager int null comment '是否书籍',
	integration int null comment '当前积分',
	level varchar(16) null comment '评议等级',
	star_member varchar(256) null comment '星级党员',
	star_manager varchar(256) null comment '星级支书',
	member_create mediumtext null comment '党员四创',
	promise mediumtext null comment '争星承诺',
	photosrc varchar(256) null comment '照片',
	org_id bigint null,
	pass int null comment '是否审核通过',
	comment varchar(512) null comment '审核意见',
	qrcode varchar(256) null
)
engine=InnoDB charset=utf8
;

create table t_menu
(
	id bigint auto_increment comment '����id'
		primary key,
	code varchar(255) null comment '�˵����',
	pid bigint null comment '�˵������',
	name varchar(255) null comment '�˵�����',
	icon varchar(255) null comment '�˵�ͼ��',
	url varchar(255) null comment 'url��ַ',
	num int(65) null comment '�˵������',
	ismenu int null comment '�Ƿ��ǲ˵���1����  0�����ǣ�',
	tips varchar(255) null comment '��ע',
	status int(65) null comment '�˵�״̬ :  1:����   0:������',
	isopen int null comment '�Ƿ��:    1:��   0:����'
)
comment '�˵���' engine=InnoDB charset=utf8
;

create table t_org
(
	id bigint auto_increment
		primary key,
	fullname varchar(256) null comment '机构名称',
	shortname varchar(256) null comment '机构简称',
	pid bigint null comment '父机构',
	code varchar(64) null comment '机构代码',
	address_detail varchar(256) null comment '机构详细地址',
	phone varchar(16) null comment '机构联系电话',
	manager_id bigint null comment '机构负责人ID',
	detail mediumtext null comment '机构详情介绍',
	status int null comment '机构状态'
)
engine=InnoDB charset=utf8
;

create table t_role
(
	id bigint auto_increment
		primary key,
	name varchar(64) null comment '角色名称',
	status int null comment '角色状态',
	can_delete int null comment '是否可以删除'
)
engine=InnoDB charset=utf8
;

create table t_role_menu
(
	id bigint auto_increment
		primary key,
	menu_id bigint null comment '菜单ID',
	role_id bigint null comment '角色ID'
)
engine=InnoDB charset=utf8
;

create table t_user
(
	id bigint auto_increment
		primary key,
	name varchar(64) null comment '人员姓名',
	card_no varchar(64) null comment '工号',
	cert_no varchar(32) null comment '身份证号',
	loginname varchar(32) null comment '登陆账号',
	password varchar(32) null comment '登陆密码',
	gender int null comment '性别',
	photosrc varchar(128) null comment '照片路径',
	home_detail varchar(256) null comment '住宅详细地址',
	phone varchar(16) null comment '工作电话',
	mobile varchar(16) null comment '移动电话',
	org_id bigint null,
	status int null,
	can_delete int null comment '是否可以删除'
)
engine=InnoDB charset=utf8
;

create table t_user_role
(
	id bigint auto_increment
		primary key,
	user_id bigint null comment '用户ID',
	role_id bigint null comment '角色ID'
)
engine=InnoDB charset=utf8
;
