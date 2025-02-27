create table t_booking(
    id uuid primary key default gen_random_uuid(),
    rack_id uuid not null references t_rack(id),
    requester_id uuid not null references t_team_member(id),
    book_from timestamp not null,
    book_to timestamp not null,
    created_at timestamp not null default now(),
    modified_at timestamp not null default now()
);