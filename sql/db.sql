
create table loan_application (
                                  loan_application_id BIGINT not null,
                                  applicant_name varchar(100) not null,
                                  status varchar(10) not null,
                                  user_id BIGINT,
                                  primary key (loan_application_id)
);

create table loan_application_checklist_item (
                                                 loan_application_id BIGINT not null,
                                                 notes varchar(1000),
                                                 status int4 not null,
                                                 check_list_task BIGINT not null,
                                                 user_id BIGINT not null,
                                                 primary key (loan_application_id)
);

create table user_entity (
                             user_id BIGINT not null,
                             is_deleted boolean not null,
                             password varchar(255) not null,
                             username varchar(100) not null,
                             primary key (user_id)
);

alter table loan_application_checklist_item
    add constraint FK3dirprcu69t9pmyvbjkry3cwn
        foreign key (check_list_task)
            references loan_application;

alter table loan_application_checklist_item
    add constraint FKp71rl297vvoxswbnq59g4vp3i
        foreign key (user_id)
            references user_entity;

insert into user_entity
(user_id, username, password, is_deleted)
values
    (1, 'nazar','$2a$12$nNblAWvGRIlwie2BaQJlSe6x7w0pAvxjdB4hiZTaEVk/jakgPH0au',false);

insert into user_entity
(user_id, username, password, is_deleted)
values
    (2, 'taras','$2a$12$nNblAWvGRIlwie2BaQJlSe6x7w0pAvxjdB4hiZTaEVk/jakgPH0au',false);