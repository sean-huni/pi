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

insert into rpi.user (name, username, password, enabled, team_id)
values ('Sean', 'demo@email.com', '$2a$10$.R9eHFOfpQDa.BEmkUUdVegl/5XUQ8ELXaKFz/7QJmqunjbVyg/0y', true, 1);

insert into RPI.TOKEN_LOG (id, series, lastUpdated, token, username)
values (1, 'KDRbKsElGjpY0abA1vwZUQ==', CURRENT_TIMESTAMP(), '83AjDxYvEBxDgLMJLNzkaA==', 'demo@email.com');