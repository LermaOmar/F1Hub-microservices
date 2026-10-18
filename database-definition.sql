-- ==========================================================
-- Fantasy F1 - esquema PostgreSQL
-- ==========================================================

CREATE TABLE account (
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    email      VARCHAR(50)  NOT NULL UNIQUE,

    password   VARCHAR(255) NOT NULL,              -- hash (BCrypt)
    rol        VARCHAR(20)  NOT NULL DEFAULT 'USER'
               CHECK (rol IN ('USER', 'ADMIN')),
    is_active  BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE TABLE league (
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(100)
);

CREATE TABLE scoring_entity (
    id                        BIGSERIAL PRIMARY KEY,
    name                      VARCHAR(100) NOT NULL,
    country                   VARCHAR(60),
    scoring_entity_type       VARCHAR(20)  NOT NULL
                              CHECK (scoring_entity_type IN ('DRIVER', 'SCUDERIA')),
    value                     NUMERIC(14,2) NOT NULL DEFAULT 0,
    is_bidable                BOOLEAN       NOT NULL DEFAULT FALSE,
    last_bid                  NUMERIC(14,2)
);

CREATE TABLE score (
    id                  BIGSERIAL PRIMARY KEY,
    puntuation          INTEGER NOT NULL,
    date                DATE    NOT NULL,
    scoring_entity_id   BIGINT  NOT NULL
                        REFERENCES scoring_entity(id) ON DELETE CASCADE
);

CREATE TABLE market (
    id         BIGSERIAL PRIMARY KEY,
    league_id  BIGINT NOT NULL UNIQUE               -- relación 1:1 con league
               REFERENCES league(id) ON DELETE CASCADE
);

CREATE TABLE team (
    id           BIGSERIAL PRIMARY KEY,
    account_id   BIGINT NOT NULL REFERENCES account(id)        ON DELETE CASCADE,
    league_id    BIGINT NOT NULL REFERENCES league(id)         ON DELETE CASCADE,
    scuderia_id  BIGINT NOT NULL REFERENCES scoring_entity(id),
    CONSTRAINT uq_team_account_league UNIQUE (account_id, league_id)
);

-- N:M team <-> scoring_entity (los 2 pilotos del equipo)
CREATE TABLE team_driver (
    team_id            BIGINT NOT NULL REFERENCES team(id)            ON DELETE CASCADE,
    scoring_entity_id  BIGINT NOT NULL REFERENCES scoring_entity(id),
    PRIMARY KEY (team_id, scoring_entity_id)
);

-- N:M market <-> scoring_entity (entidades pujables)
CREATE TABLE market_scoring_entity (
    market_id          BIGINT NOT NULL REFERENCES market(id)          ON DELETE CASCADE,
    scoring_entity_id  BIGINT NOT NULL REFERENCES scoring_entity(id)  ON DELETE CASCADE,
    PRIMARY KEY (market_id, scoring_entity_id)
);

-- Índices sobre FKs
CREATE INDEX idx_team_account        ON team(account_id);
CREATE INDEX idx_team_league         ON team(league_id);
CREATE INDEX idx_team_scuderia       ON team(scuderia_id);
CREATE INDEX idx_score_entity_date   ON score(scoring_entity_id, date);