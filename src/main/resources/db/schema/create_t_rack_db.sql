create table t_rack (
    id uuid primary key default gen_random_uuid(),
    serial_number varchar(15) unique,
    status varchar(20) not null constraint status_constraint CHECK ( status in ('AVAILABLE', 'BOOKED', 'UNAVAILABLE')),
    team_id uuid references t_team(id),
    default_location varchar(50) not null,
    created_at timestamp not null default now(),
    modified_at timestamp not null default now()
);