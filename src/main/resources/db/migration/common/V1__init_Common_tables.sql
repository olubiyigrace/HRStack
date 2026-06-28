create table users
(
    id                varchar(255) not null primary key,
    created_at        timestamp(6) not null,
    updated_at        timestamp(6),
    deleted_at        timestamp(6),
    email             varchar(255) not null,
    company_name      varchar(255) not null,
    is_verified       boolean      not null,
    workspace_url     varchar(255) not null unique,
    password          varchar(255) not null,

    phone             varchar(255) not null,
    institution_id    varchar(255),
    role              varchar(255) not null
        constraint users_role_check
            check (
                (role)::text = ANY (
        ARRAY[
        ('ADMIN':: character varying)::text,
        ('MANAGER':: character varying)::text,
        ('EMPLOYEE':: character varying)::text
        ]
        )
) );



create table otp
(
    id      varchar(255) not null primary key,
    email   varchar(255) not null,
    otp     varchar(255) not null,
    purpose varchar(255) not null,
    used    boolean      not null
);