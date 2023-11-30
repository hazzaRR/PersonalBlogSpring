create table if not exists user (
    user_id BIGSERIAL PRIMARY KEY,
    username varchar not null,
    firstname varchar not null,
    surname varchar not null,
    email varchar not null,
    profile_picture varchar not null,
    UNIQUE (email),
    UNIQUE (username)
    );

CREATE TABLE if not exists post (
    post_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    author_id BIGINT REFERENCES user(user_id),
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
    author_id BIGINT REFERENCES user(user_id),
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
    post_id BIGSERIAL REFERENCES post(post_id),
    category_id BIGINT REFERENCES category(category_id),
    PRIMARY KEY (post_id, category_id)
);

