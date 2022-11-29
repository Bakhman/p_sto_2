insert into role (id, name)
values (100, 'USER');


INSERT INTO user_entity (
    id,
    email,
    password,
    full_name,
    persist_date,
    is_enabled,
    is_deleted,
    city,
    link_site,
    link_github,
    link_vk,
    about,
    image_link,
    last_redaction_date,
    nickname,
    role_id)
VALUES (
           100,
           'user100@mail.ru',
           '12345',
           'user100',
           '2022-10-12T17:06:24.197649',
           true,
           false,
           'user100_city',
           'user100@site.com',
           'user100@github.com',
           'user100@vk.com',
           'test_user100',
           'null',
           CURRENT_TIMESTAMP ,
           'user100',
           100),
       (
           101,
           'user101@mail.ru',
           '$2a$12$RbzrY3EwB4O/UElqKDY..e8e8t88SijJ0iVtMBE0VYsQUxV6Wtvhu',
           'user101',
           '2022-9-12T17:05:24.197649',
           true,
           NULL,
           'user101_city',
           'user101site.com',
           'user101github.com',
           'user101vk.com',
           'test_user101',
           '',
           CURRENT_TIMESTAMP,
           'user101',
           100),

       (
           102,
           'user102@mail.ru',
           '$2a$12$RbzrY3EwB4O/UElqKDY..e8e8t88SijJ0iVtMBE0VYsQUxV6Wtvhu',
           'user102',
           '2022-8-12T17:05:24.197649',
           true,
           NULL,
           'user102_city',
           'user102site.com',
           'user102github.com',
           'user102vk.com',
           'test_user102',
           '',
           CURRENT_TIMESTAMP,
           'user102',
           100);

insert into question (id,
                      title,
                      description,
                      persist_date,
                      user_id,
                      last_redaction_date,
                      is_deleted)
values (100, 'title0', 'description0', '2022-10-20T16:46:51.936176', 100, '2022-10-26T17:46:51.936176', false),
       (101, 'title1', 'description1', '2022-10-21T17:11:51.936176', 101, '2022-10-28T18:11:51.936176', false),
       (102, 'title2', 'description2', '2022-10-23T17:12:51.936176', 102, '2022-10-29T18:23:51.936176', false),
       (103, 'title3', 'description3', '2022-10-24T17:16:51.936176', 101, '2022-10-28T18:41:51.936176', false),
       (104, 'title4', 'description4', '2022-10-25T17:18:51.936176', 102, '2022-10-28T18:47:51.936176', false),
       (105, 'title5', 'description5', '2022-10-26T17:24:51.936176', 101, '2022-10-28T18:47:51.936176', false),
       (106, 'title6', 'description6', '2022-10-27T17:44:51.936176', 100, '2022-10-28T18:55:51.936176', false),
       (107, 'title7', 'description7', '2019-10-27T17:44:51.936176', 100, '2019-11-28T18:55:51.936176', false);


insert into question_viewed (id, user_id, question_id, persist_date)
values (100, '101', '100', '2022-10-26T17:46:51.936176'),
       (101, '102', '100', '2022-10-26T17:46:51.936176');

insert into bookmarks (id, user_id, question_id, persist_date)
values  (100, '102', '104', '2022-11-02T11:23:51.936176');

insert into tag (id, name, description, persist_date)
values ('100', 'tag100', 'it is tag 100', '2022-11-02T11:23:51.936176'),
       ('101', 'tag101', 'it is tag 101', '2022-11-02T11:25:53.936176'),
       ('102', 'tag102', 'it is tag 102', '2022-11-02T11:32:55.936176'),
       ('103', 'tag103', 'it is tag 103', '2022-11-02T11:43:56.936176'),
       ('104', 'tag104', 'it is tag 104', '2022-11-02T11:45:51.936176'),
       ('105', 'tag105', 'it is tag 105', '2022-11-02T11:46:51.936176'),
       ('106', 'tag106', 'it is tag 106', '2022-11-02T11:47:51.936176');

insert into votes_on_questions (id, user_id, question_id, persist_date, vote)
values (100, '100', '101', '2022-10-20T16:46:51.936176', 'UP_VOTE'),
       (101, '101', '101', '2022-10-20T17:46:51.936176', 'UP_VOTE'),
       (102, '102', '101', '2022-10-20T17:56:51.936176', 'UP_VOTE'),


       (103, '100', '102', '2022-10-20T18:14:51.936176', 'DOWN_VOTE'),
       (104, '101', '102', '2022-10-20T18:46:51.936176', 'DOWN_VOTE'),
       (105, '102', '102', '2022-10-20T19:26:51.936176', 'DOWN_VOTE'),


       (106, '100', '103', '2022-10-20T20:22:51.936176', 'UP_VOTE'),
       (107, '101', '103', '2022-10-20T21:46:51.936176', 'DOWN_VOTE'),
       (108, '102', '103', '2022-10-20T22:46:51.936176', 'UP_VOTE');



INSERT INTO answer (id,
                    persist_date,
                    update_date,
                    question_id,
                    user_id,
                    html_body,
                    is_helpful,
                    is_deleted,
                    is_deleted_by_moderator,
                    date_accept_time)
VALUES (100,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        100,
        101,
        'User104 answer for Question_100',
        TRUE,
        FALSE,
        FALSE,
        CURRENT_TIMESTAMP),
       (101,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        100,
        102,
        'User105 answer for Question_100',
        FALSE,
        FALSE,
        FALSE,
        CURRENT_TIMESTAMP),
       (102,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        101,
        100,
        'User106 answer for Question_101',
        TRUE,
        FALSE,
        FALSE,
        CURRENT_TIMESTAMP),
       (103,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        101,
        102,
        'User107 answer for Question_101',
        FALSE,
        FALSE,
        FALSE,
        CURRENT_TIMESTAMP),
       (104,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        102,
        100,
        'User108 answer for Question_102',
        TRUE,
        FALSE,
        FALSE,
        CURRENT_TIMESTAMP),
       (105,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        102,
        101,
        'User109 answer for Question_102',
        FALSE,
        FALSE,
        FALSE,
        CURRENT_TIMESTAMP),
       (106,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        104,
        100,
        'User110 answer for Question_103',
        TRUE,
        FALSE,
        FALSE,
        CURRENT_TIMESTAMP);


insert into question_has_tag (question_id, tag_id)
values ('100', '100'),
       ('100', '101'),
       ('101', '102'),
       ('101', '103'),
       ('102', '101'),
       ('103', '102'),
       ('103', '103'),
       ('104', '101'),
       ('104', '105'),
       ('105', '105'),
       ('105', '106'),
       ('106', '103'),
       ('106', '104'),
       ('107', '100');


insert into reputation (id, persist_date, author_id, sender_id, count, type, question_id, answer_id)
values (100, current_timestamp, 101, null, 500, 0, 100, null),
       (101, current_timestamp, 102, null, 300, 0, 101, null),
       (102, current_timestamp, 100, null, 600, 0, 102, null);