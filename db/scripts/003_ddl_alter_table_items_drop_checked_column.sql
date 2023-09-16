create table items (
    id serial primary key,
    name text,
    created timestamp
);

ALTER TABLE items ADD COLUMN checked BOOLEAN NOT NULL DEFAULT false;

alter table items DROP column checked;