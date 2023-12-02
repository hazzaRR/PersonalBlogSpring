create table if not exists users (
    user_id BIGSERIAL PRIMARY KEY,
    username varchar not null,
    password varchar not null,
    firstname varchar,
    surname varchar,
    email varchar,
    profile_picture varchar,
    UNIQUE (email),
    UNIQUE (username)
    );

CREATE TABLE if not exists post (
    post_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    author_id BIGINT REFERENCES users(user_id),
    date DATE,
    post_body TEXT,
    private_post BOOLEAN
);

CREATE TABLE if not exists category (
    category_id BIGSERIAL PRIMARY KEY,
    category_name VARCHAR(255)
);

CREATE TABLE if not exists comment (
    comment_id BIGSERIAL PRIMARY KEY,
    post_id BIGINT REFERENCES post(post_id),
    author_id BIGINT REFERENCES users(user_id),
    date DATE,
    comment_body TEXT
);

CREATE TABLE if not exists post_image (
    post_image_id BIGSERIAL PRIMARY KEY,
    post_id BIGINT REFERENCES post(post_id),
    photo_url VARCHAR(255),
    metadata TEXT
    );


CREATE TABLE if not exists blogpost_category (
    post_id BIGINT REFERENCES post(post_id),
    category_id BIGINT REFERENCES category(category_id),
    PRIMARY KEY (post_id, category_id)
);

CREATE TABLE if not exists roles (
    role_id BIGSERIAL,
    authority varchar,
    PRIMARY KEY (role_id)
);

CREATE TABLE if not exists user_role (
    user_id BIGINT REFERENCES users(user_id),
    role_id BIGINT REFERENCES roles(role_id),
    PRIMARY KEY (user_id, role_id)
);



-- Create roles
INSERT INTO roles (role_id, authority) VALUES (1, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO roles (role_id, authority) VALUES (2, 'ROLE_READER') ON CONFLICT DO NOTHING;
INSERT INTO roles (role_id, authority) VALUES (3, 'ROLE_AUTHOR') ON CONFLICT DO NOTHING;

-- Create a default user
INSERT INTO users (username, password, firstname) VALUES ('admin', '$2a$10$aOhmoNZNjM4mcHRjtajZ1Oyt8MQrBCvGI3kMwKFIOYEX01qzB3H.y', 'admin') ON CONFLICT DO NOTHING;

-- Assign roles to the default user
INSERT INTO user_role (user_id, role_id) VALUES ((SELECT user_id FROM users WHERE username = 'admin'), (SELECT role_id FROM roles WHERE authority = 'ROLE_ADMIN')) ON CONFLICT DO NOTHING;

