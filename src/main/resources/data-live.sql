insert into rpi.team (fk_auth_id, fk_grp_id) values (1, 1);
insert into rpi.team (fk_auth_id, fk_grp_id) values (2, 2);
insert into rpi.team (fk_auth_id, fk_grp_id) values (3, 3);
insert into rpi.team (fk_auth_id, fk_grp_id) values (4, 4);

insert into rpi.auth (level) values ('USER');
insert into rpi.auth (level) values ('ADMIN');
insert into rpi.auth (level) values ('SUPER');
insert into rpi.auth (level) values ('ANONYMOUS');

insert into rpi.grp (name) values ('GREEN');
insert into rpi.grp (name) values ('ORANGE');
insert into rpi.grp (name) values ('RED');
insert into rpi.grp (name) values ('YELLOW');

insert into rpi.user (name, username, password, enabled, team_id, last_updated)
values ('Sean', 'demo@email.com', '$2a$10$.R9eHFOfpQDa.BEmkUUdVegl/5XUQ8ELXaKFz/7QJmqunjbVyg/0y', true, 1,
        CURRENT_TIMESTAMP()); -- password=password