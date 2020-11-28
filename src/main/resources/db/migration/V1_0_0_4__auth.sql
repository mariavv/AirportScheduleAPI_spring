CREATE TABLE users
(
    id            serial PRIMARY KEY,
    name          varchar(255) UNIQUE,
    password      varchar(255) NOT NULL
);

CREATE TABLE roles
(
    id            serial PRIMARY KEY,
    role          varchar(255) UNIQUE
);

CREATE TABLE user_roles
(
    id            serial PRIMARY KEY,
    user_id       integer,
    role_id       integer,
    CONSTRAINT unique_user_role UNIQUE (user_id, role_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);