create table users
(
    id              varchar(255) not null primary key,
    created_at      timestamp(6) not null,
    updated_at      timestamp(6),
    deleted_at      timestamp(6),
    email           varchar(255) not null,
    job_title       varchar(255),
    department      varchar(255),
    phone_number    varchar(255),
    image_url       varchar(255),
    image_public_id varchar(255),
    company_name    varchar(255) not null,
    first_name      varchar(255) not null,
    last_name       varchar(255) not null,
    is_verified     boolean      not null,
    workspace_url   varchar(255) not null unique,
    password        varchar(255) not null,
    role            varchar(255) not null
        constraint users_role_check
            check (
                (role)::text = ANY (
        ARRAY[
        ('ADMIN':: character varying)::text,
        ('MANAGER':: character varying)::text,
        ('EMPLOYEE':: character varying)::text
        ]
)),
    status          varchar(255)
        constraint users_status_check
            check (
                (status)::text = ANY (
        ARRAY[
        ('ACCEPTED':: character varying)::text,
        ('PENDING':: character varying)::text,
        ('DECLINED':: character varying)::text
        ]
)),
    reports_to          varchar(255)
        constraint users_reports_to_check
            check (
                (reports_to)::text = ANY (
        ARRAY[
        ('ADMIN':: character varying)::text,
        ('MANAGER':: character varying)::text
        ]
        ))
    );



create table otp
(
    id         bigserial primary key,
    email      varchar(255) not null,
    otp        varchar(255) not null,
    used       boolean      not null,
    created_at timestamp(6) not null,
    expires_at timestamp(6) not null,
    purpose    varchar(255) not null
        constraint otp_purpose_check
            check (
                (purpose)::text = ANY (
        ARRAY[
        ('VERIFY_ACCOUNT':: character varying)::text,
        ('RESET_PASSWORD':: character varying)::text
        ]
        )
) );