# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table board (
  id                            bigint auto_increment not null,
  name                          varchar(128) not null,
  user_id                       bigint not null,
  liking                        tinyint(1) default 0,
  constraint pk_board primary key (id)
);

create table card (
  id                            bigint auto_increment not null,
  list_id                       bigint not null,
  name                          varchar(128) not null,
  description                   varchar(1000),
  is_archived                   tinyint(1) default 0,
  constraint pk_card primary key (id)
);

create table comment (
  id                            bigint auto_increment not null,
  text                          varchar(1000) not null,
  card_id                       bigint not null,
  user_id                       bigint not null,
  constraint pk_comment primary key (id)
);

create table list (
  id                            bigint auto_increment not null,
  board_id                      bigint not null,
  name                          varchar(128) not null,
  is_archived                   tinyint(1) default 0,
  constraint pk_list primary key (id)
);

create table log (
  id                            bigint auto_increment not null,
  log                           varchar(350) not null,
  board_id                      bigint not null,
  constraint pk_log primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  name                          varchar(64) not null,
  email                         varchar(255) not null,
  password                      varbinary(64) not null,
  constraint uq_user_name unique (name),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id)
);

alter table card add constraint fk_card_list_id foreign key (list_id) references list (id) on delete restrict on update restrict;
create index ix_card_list_id on card (list_id);

alter table list add constraint fk_list_board_id foreign key (board_id) references board (id) on delete restrict on update restrict;
create index ix_list_board_id on list (board_id);


# --- !Downs

alter table card drop foreign key fk_card_list_id;
drop index ix_card_list_id on card;

alter table list drop foreign key fk_list_board_id;
drop index ix_list_board_id on list;

drop table if exists board;

drop table if exists card;

drop table if exists comment;

drop table if exists list;

drop table if exists log;

drop table if exists user;

