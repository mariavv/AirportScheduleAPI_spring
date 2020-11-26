CREATE TABLE permissions
(
    id            serial PRIMARY KEY,
    permission    varchar(255) UNIQUE
);

CREATE TABLE role_permissions
(
    id            serial PRIMARY KEY,
    role_id       integer,
    permission_id integer,
    CONSTRAINT unique_role_permission UNIQUE (role_id, permission_id),
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_permission_id FOREIGN KEY (permission_id)
        REFERENCES public.permissions (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);