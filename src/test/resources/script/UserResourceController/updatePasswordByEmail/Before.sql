INSERT INTO role (id, name)VALUES (100, 'USER');

INSERT INTO user_entity (id,
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

VALUES (100,
        'user100@mail.ru',
        '$2a$12$dQgY.AqyPPKVFxf0PB98dOn4kK/qwKQr9w1SQuT.Xyg.LkIjBTd1q', /*Password!1*/
        'user100',
        '2022-01-12T17:05:24.197649',
        true,
        NULL,
        'user100_city',
        'user100site.com',
        'user100github.com',
        'user100vk.com',
        'test_user100',
        '\image_user100.jpg',
        CURRENT_TIMESTAMP,
        'user100',
        100);