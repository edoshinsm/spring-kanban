insert into users (id, username, name, surname, password_hash, email) values
(next value for user_id_seq, 'user1', 'сергей', 'едошин', 'hash1', 'user1@mail.com'),
(next value for user_id_seq, 'user2', 'иван', 'иванов', 'hash2', 'user2@mail.com');

insert into projects (id, name, created_at) values
(next value for project_id_seq, 'имя_проекта1', now()),
(next value for project_id_seq, 'имя_проекта2', now()),
(next value for project_id_seq, 'имя_проекта3', now()),
(next value for project_id_seq, 'имя_проекта4', now());

insert into columns (id, name, project_id, position) values
(next value for column_id_seq, 'колонка1', 1, 1),
(next value for column_id_seq, 'колонка2', 1, 2),
(next value for column_id_seq, 'колонка3', 1, 3),
(next value for column_id_seq, 'колонка4', 1, 4),
(next value for column_id_seq, 'колонка5', 1, 5),
(next value for column_id_seq, 'колонка6', 1, 6),
(next value for column_id_seq, 'колонка1', 2, 1);

insert into issues (id, name, description, column_id, created_at, status, due_date, creator_id) values
(next value for issue_id_seq, 'задание1', 'описание', 1, '2026-01-01 10:00:00', 'OPEN', '2026-02-01 10:00:00', 1),
(next value for issue_id_seq, 'задание2', 'описание', 1, '2026-01-01 10:00:00', 'OPEN', '2026-02-01 10:00:00', 1),
(next value for issue_id_seq, 'задание3', 'описание', 1, '2026-01-01 10:00:00', 'OPEN', '2026-02-01 10:00:00', 1),
(next value for issue_id_seq, 'задание4', 'описание', 1, '2026-01-01 10:00:00', 'OPEN', '2026-02-01 10:00:00', 1),
(next value for issue_id_seq, 'задание1', 'описание', 2, '2026-01-01 10:00:00', 'OPEN', '2026-02-01 10:00:00', 1),
(next value for issue_id_seq, 'задание2', 'описание', 2, '2026-01-01 10:00:00', 'OPEN', '2026-02-01 10:00:00', 1),
(next value for issue_id_seq, 'задание3', 'описание', 2, '2026-01-01 10:00:00', 'OPEN', '2026-02-01 10:00:00', 1);

insert into project_members (id, role, user_id, project_id) values
(next value for project_members_id_seq, 'ADMIN', 1, 1),
(next value for project_members_id_seq, 'VIEWER', 2, 1);