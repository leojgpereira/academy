create table t_team(
    id uuid primary key default gen_random_uuid(),
    name varchar(50) not null,
    product varchar(50) not null,
    default_location varchar(50) not null,
    created_at timestamp not null default now(),
    modified_at timestamp not null default now()
);