-- add users
-- password=strings
insert into users(email, password)
values ('string@gmail.com', '$2a$10$l3/OyHy.gPD7s4f98PVQ8Obg/CfKxeifY2kxrkoHnow6GTa7HOg6G');
-- add courses
insert into course(title, description, language)
values
-- courses RUSSIAN language
('Java',
 'JAVA разработчик создает приложения для различных платформ, включая Android и веб-приложения.',
 'RUSSIAN'),
('Python',
 'Python разработчик (Python developer) отвечает за внутреннюю логику сайта и приложений.',
 'RUSSIAN'),
('Front-End',
 'Frontend-разработчик (frontend developer) — это специалист,
который отвечает за создание пользовательского интерфейса сайта.',
 'RUSSIAN'),
('UX/UI Designer',
 'UX/UI Designer - специалист который
отвечает за дизайн сайта и мобиль-
ных приложений. ',
 'RUSSIAN'),
-- courses KYRGYZSTAN language
('Java',
 'JAVA иштеп чыгуучусу ар кандай платформалар үчүн тиркемелерди түзөт, анын ичинде Android жана веб-тиркемелер.',
 'KYRGYZSTAN'),
('Python',
 'Python иштеп чыгуучусу сайттын жана тиркемелердин ички логикасы үчүн жооптуу.',
 'KYRGYZSTAN'),
('Front-End',
 'Frontend иштеп чыгуучу адис болуп саналат
сайттын колдонуучу интерфейсин түзүү үчүн жооптуу ким.',
 'KYRGYZSTAN'),
('UX/UI Designer',
 'UX/UI Designer - бул адис
сайт дизайн жана мобилдик үчүн жооптуу
жаңы колдонмолор.',
 'KYRGYZSTAN');
-- add diplomas
insert into diploma(title, language, course_id)
values
    -- Diplomas for Java (RUSSIAN)
    ('Разработка консольного приложения', 'RUSSIAN', 1),
    ('Создание веб-приложения', 'RUSSIAN', 1),
    ('Разработка Android-приложения', 'RUSSIAN', 1),
    ('Автоматизация тестирования', 'RUSSIAN', 1),

    -- Diplomas for Python (RUSSIAN)
    ('Создание скрипта для веб-скрейпинга', 'RUSSIAN', 2),
    ('Разработка бота для Telegram', 'RUSSIAN', 2),
    ('Машинное обучение с использованием Python', 'RUSSIAN', 2),
    ('Анализ данных и визуализация', 'RUSSIAN', 2),

    -- Diplomas for Front-End (RUSSIAN)
    ('Создание интерактивного сайта', 'RUSSIAN', 3),
    ('Разработка одностраничного приложения (SPA)', 'RUSSIAN', 3),
    ('Вёрстка адаптивного сайта', 'RUSSIAN', 3),
    ('Оптимизация производительности сайта', 'RUSSIAN', 3),

    -- Diplomas for UX/UI Designer (RUSSIAN)
    ('Проектирование мобильного интерфейса', 'RUSSIAN', 4),
    ('Создание прототипа веб-приложения', 'RUSSIAN', 4),
    ('Дизайн системы навигации', 'RUSSIAN', 4),
    ('Разработка брендинга для приложения', 'RUSSIAN', 4),

    -- Diplomas for Java (KYRGYZSTAN)
    ('Консольдук колдонмо иштеп чыгуу', 'KYRGYZSTAN', 5),
    ('Веб-тиркемени түзүү', 'KYRGYZSTAN', 5),
    ('Android колдонмо иштеп чыгуу', 'KYRGYZSTAN', 5),
    ('Тестирлөөнү автоматташтыруу', 'KYRGYZSTAN', 5),

    -- Diplomas for Python (KYRGYZSTAN)
    ('Веб скрейпинг үчүн скрипт түзүү', 'KYRGYZSTAN', 6),
    ('Telegram үчүн бот иштеп чыгуу', 'KYRGYZSTAN', 6),
    ('Python колдонуу менен машиналык үйрөнүү', 'KYRGYZSTAN', 6),
    ('Маалыматтарды анализдөө жана визуалдаштыруу', 'KYRGYZSTAN', 6),

    -- Diplomas for Front-End (KYRGYZSTAN)
    ('Интерактивдүү сайтты түзүү', 'KYRGYZSTAN', 7),
    ('Бир беттик колдонмо (SPA) иштеп чыгуу', 'KYRGYZSTAN', 7),
    ('Адаптивдүү сайттын версткасы', 'KYRGYZSTAN', 7),
    ('Сайттын өндүрүмдүүлүгүн оптималдаштыруу', 'KYRGYZSTAN', 7),

    -- Diplomas for UX/UI Designer (KYRGYZSTAN)
    ('Мобилдик интерфейсти долбоорлоо', 'KYRGYZSTAN', 8),
    ('Веб-тиркеменин прототибин түзүү', 'KYRGYZSTAN', 8),
    ('Навигациялык системанын дизайны', 'KYRGYZSTAN', 8),
    ('Колдонмо үчүн брендинг иштеп чыгуу', 'KYRGYZSTAN', 8);
-- add subjects
insert into subject(title, language, course_id)
values ('Java', 'RUSSIAN', 1),
       ('JDBC', 'RUSSIAN', 1),
       ('Hibernate', 'RUSSIAN', 1),
       ('Spring MVC', 'RUSSIAN', 1),
       ('Spring Security', 'RUSSIAN', 1),
       ('Spring Boot', 'RUSSIAN', 1),
       ('Java', 'KYRGYZSTAN', 5),
       ('JDBC', 'KYRGYZSTAN', 5),
       ('Hibernate', 'KYRGYZSTAN', 5),
       ('Spring MVC', 'KYRGYZSTAN', 5),
       ('Spring Security', 'KYRGYZSTAN', 5),
       ('Spring Boot', 'KYRGYZSTAN', 5),
       ('Python', 'RUSSIAN', 2),
       ('OOP', 'RUSSIAN', 2),
       ('GIT/GITHUB', 'RUSSIAN', 2),
       ('PostgreSQL', 'RUSSIAN', 2),
       ('DJANGO FRAMEWORK', 'RUSSIAN', 2),
       ('Python', 'KYRGYZSTAN', 6),
       ('OOP', 'KYRGYZSTAN', 6),
       ('GIT/GITHUB', 'KYRGYZSTAN', 6),
       ('PostgreSQL', 'KYRGYZSTAN', 6),
       ('DJANGO FRAMEWORK', 'KYRGYZSTAN', 6),
       ('HTML-CSS', 'RUSSIAN', 3),
       ('JAVASCRIPT', 'RUSSIAN', 3),
       ('REACT JS', 'RUSSIAN', 3),
       ('REDUX', 'RUSSIAN', 3),
       ('REDUX-TOOLKIT', 'RUSSIAN', 3),
       ('MATERIAL-UI', 'RUSSIAN', 3),
       ('HTML-CSS', 'KYRGYZSTAN', 7),
       ('JAVASCRIPT', 'KYRGYZSTAN', 7),
       ('REACT JS', 'KYRGYZSTAN', 7),
       ('REDUX', 'KYRGYZSTAN', 7),
       ('REDUX-TOOLKIT', 'KYRGYZSTAN', 7),
       ('MATERIAL-UI', 'KYRGYZSTAN', 7),
       ('Веб - верстка', 'RUSSIAN', 4),
       ('JavaScript', 'RUSSIAN', 4),
       ('React JS', 'RUSSIAN', 4),
       ('Веб - верстка', 'KYRGYZSTAN', 8),
       ('JavaScript', 'KYRGYZSTAN', 8),
       ('React JS', 'KYRGYZSTAN', 8);
-- add directions
insert into direction(title)
values ('Java'),
       ('Python'),
       ('Front-End'),
       ('UX/UI Designer');;
-- add projects
insert into project(title, description, language, link)
values ('BAITIK LUX',
        'Baytik Lux Residence — это закрытый комплекс, где вы можете наслаждаться комфортом и роскошью в безопасной и приватной обстановке.',
        'RUSSIAN',
        'https://'),
       ('BAITIK LUX',
        'Baytik Lux Residence — это закрытый комплекс, где вы можете наслаждаться комфортом и роскошью',
        'RUSSIAN',
        'https://'),
       ('BAITIK LUX',
        'Baytik Lux Residence — это закрытый комплекс, где вы можете наслаждаться комфортом и роскошью',
        'RUSSIAN',
        'https://'),
       ('BAITIK LUX',
        'Baytik Lux Residence — это закрытый комплекс, где вы можете наслаждаться комфортом и роскошью',
        'RUSSIAN',
        'https://'),
       ('BAITIK LUX',
        'Baytik Lux Residence — это закрытый комплекс, где вы можете наслаждаться комфортом и роскошью',
        'RUSSIAN',
        'https://'),
       ('BAITIK LUX',
        'Baytik Lux Residence - бул коопсуз жана жеке чөйрөдө комфорт жана люкс ырахат ала турган дарбазалуу жамаат.',
        'KYRGYZSTAN',
        'https://'),
       ('BAITIK LUX',
        'Baytik Lux Residence - бул дарбазалуу комплекс, анда сиз комфорт жана кымбатчылыктан ырахат ала аласыз',
        'KYRGYZSTAN',
        'https://'),
       ('BAITIK LUX',
        'Baytik Lux Residence - бул дарбазалуу комплекс, анда сиз комфорт жана кымбатчылыктан ырахат ала аласыз',
        'KYRGYZSTAN',
        'https://'),
       ('BAITIK LUX',
        'Baytik Lux Residence - бул дарбазалуу комплекс, анда сиз комфорт жана кымбатчылыктан ырахат ала аласыз',
        'KYRGYZSTAN',
        'https://'),
       ('BAITIK LUX',
        'Baytik Lux Residence - бул дарбазалуу комплекс, анда сиз комфорт жана кымбатчылыктан ырахат ала аласыз',
        'KYRGYZSTAN',
        'https://');
-- add employees
insert into employee(full_name, link_linked_in)
values ('Иван Иванов', 'https://www.linkedin.com/in/ivan-ivanov'),
       ('Мария Петрова', 'https://www.linkedin.com/in/maria-petrova'),

       ('Алексей Смирнов', 'https://www.linkedin.com/in/alexey-smirnov'),
       ('Ольга Кузнецова', 'https://www.linkedin.com/in/olga-kuznetsova'),

       ('Дмитрий Соколов', 'https://www.linkedin.com/in/dmitry-sokolov'),
       ('Екатерина Иванова', 'https://www.linkedin.com/in/ekaterina-ivanova'),

       ('Андрей Попов', 'https://www.linkedin.com/in/andrey-popov'),
       ('Анна Васильева', 'https://www.linkedin.com/in/anna-vasilyeva');
-- add relationship for employee_course
insert into employee_course(employee_id, course_id)
values (1, 1),
       (2, 5),
       (3, 2),
       (4, 6),
       (5, 3),
       (6, 7),
       (7, 4),
       (8, 8);
-- add relationship for employee_direction
insert into employee_direction(employee_id, direction_id)
values (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 3),
       (6, 3),
       (7, 4),
       (8, 4);