create table t_rack_asset(
    id uuid primary key default gen_random_uuid(),
    asset_tag varchar(20) not null,
    rack_id uuid not null references t_rack(id)
);