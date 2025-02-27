create table t_team_member(
    id uuid primary key default gen_random_uuid(),
    team_id uuid references t_team(id),
    ctw_id varchar(10) not null,
    name varchar(50) not null,
    created_at timestamp not null default now(),
    modified_at timestamp not null default now()
);