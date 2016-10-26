# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table board (
  id                            bigint auto_increment not null,
  name                          varchar(128) not null,
  constraint pk_board primary key (id)
);

create table board_user (
  board_id                      bigint not null,
  user_id                       bigint not null,
  constraint pk_board_user primary key (board_id,user_id)
);

create table card (
  id                            bigint auto_increment not null,
  list_id                       bigint not null,
  name                          varchar(128) not null,
  description                   varchar(1000),
  constraint pk_card primary key (id)
);

create table list (
  id                            bigint auto_increment not null,
  board_id                      bigint not null,
  name                          varchar(128) not null,
  constraint pk_list primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  name                          varchar(64) not null,
  email                         varchar(255) not null,
  password                      varchar(64) not null,
  constraint uq_user_name unique (name),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id)
);

create table user_board (
  user_id                       bigint not null,
  board_id                      bigint not null,
  constraint pk_user_board primary key (user_id,board_id)
);

alter table board_user add constraint fk_board_user_board foreign key (board_id) references board (id) on delete restrict on update restrict;
create index ix_board_user_board on board_user (board_id);

alter table board_user add constraint fk_board_user_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_board_user_user on board_user (user_id);

alter table card add constraint fk_card_list_id foreign key (list_id) references list (id) on delete restrict on update restrict;
create index ix_card_list_id on card (list_id);

alter table list add constraint fk_list_board_id foreign key (board_id) references board (id) on delete restrict on update restrict;
create index ix_list_board_id on list (board_id);

alter table user_board add constraint fk_user_board_user foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_board_user on user_board (user_id);

alter table user_board add constraint fk_user_board_board foreign key (board_id) references board (id) on delete restrict on update restrict;
create index ix_user_board_board on user_board (board_id);


# --- !Downs

alter table board_user drop foreign key fk_board_user_board;
drop index ix_board_user_board on board_user;

alter table board_user drop foreign key fk_board_user_user;
drop index ix_board_user_user on board_user;

alter table card drop foreign key fk_card_list_id;
drop index ix_card_list_id on card;

alter table list drop foreign key fk_list_board_id;
drop index ix_list_board_id on list;

alter table user_board drop foreign key fk_user_board_user;
drop index ix_user_board_user on user_board;

alter table user_board drop foreign key fk_user_board_board;
drop index ix_user_board_board on user_board;

drop table if exists board;

drop table if exists board_user;

drop table if exists card;

drop table if exists list;

drop table if exists user;

drop table if exists user_board;

