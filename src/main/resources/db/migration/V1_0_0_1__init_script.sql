CREATE SEQUENCE IF NOT EXISTS hibernate_sequence start 1000 increment 1;

CREATE TABLE IF NOT EXISTS Airport
(
    id            serial PRIMARY KEY,
    name          varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Flight
(
    id                      serial PRIMARY KEY,
    airport_from_id         integer NOT NULL,
    airport_to_id           integer NOT NULL,
    departure               timestamp without time zone,
    arrival                 timestamp without time zone,
    delay                   time without time zone,
    delay_arrival           time without time zone,
    postponed_on_flight_id  integer,
    is_canceled  boolean
);