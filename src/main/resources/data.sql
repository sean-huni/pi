insert into rpi.g_auth (id) values (1);
insert into rpi.g_auth (id) values (2);
insert into rpi.g_auth (id) values (3);
insert into rpi.g_auth (id) values (4);

insert into rpi.auth (id, level) values (1, 'USER');
insert into rpi.auth (id, level) values (2, 'ADMIN');
insert into rpi.auth (id, level) values (3, 'SUPER');
insert into rpi.auth (id, level) values (4, 'ANONYMOUS');

insert into rpi.grp (name) values ('GREEN');
insert into rpi.grp (name) values ('ORANGE');
insert into rpi.grp (name) values ('RED');
insert into rpi.grp (name) values ('YELLOW');

insert into rpi.team (grp_id, grp_auth_id) values (1, 1);

insert into rpi.user (username, password, enabled, team_id)
values ('demo@email.com', 'ebde6e599feaa84afdf86ab799964e60', true, 1)
