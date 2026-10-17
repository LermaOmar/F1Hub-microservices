# F1 HUB

Re-adaption of F1 HUB, now integrated with multiple microservices

## DATA BASE DEFINITION

erDiagram
ACCOUNT {
bigint id PK
varchar username UK
varchar password
varchar rol "USER | ADMIN"
boolean is_active
}
```mermaid
erDiagram
    ACCOUNT {
        bigint id PK
        varchar username UK
        varchar password
        varchar rol "USER | ADMIN"
        boolean is_active
    }

    LEAGUE {
        bigint id PK
        varchar name
    }

    MARKET {
        bigint id PK
        bigint league_id FK, UK
    }

    TEAM {
        bigint id PK
        bigint account_id FK
        bigint league_id FK
        bigint scuderia_id FK
    }

    SCORING_ENTITY {
        bigint id PK
        varchar name
        varchar country
        varchar scoring_entity_type "DRIVER | SCUDERIA"
        numeric value
        boolean is_bidable
        numeric last_bid
    }

    SCORE {
        bigint id PK
        int puntuation
        date date
        bigint scoring_entity_id FK
    }

    TEAM_DRIVER {
        bigint team_id PK, FK
        bigint scoring_entity_id PK, FK
    }

    MARKET_SCORING_ENTITY {
        bigint market_id PK, FK
        bigint scoring_entity_id PK, FK
    }

    ACCOUNT ||--o{ TEAM : "tiene"
    LEAGUE ||--o{ TEAM : "contiene"
    LEAGUE ||--|| MARKET : "tiene"
    SCORING_ENTITY ||--o{ SCORE : "recibe"
    SCORING_ENTITY ||--o{ TEAM : "es escudería de"
    TEAM ||--o{ TEAM_DRIVER : "incluye"
    SCORING_ENTITY ||--o{ TEAM_DRIVER : "es piloto en"
    MARKET ||--o{ MARKET_SCORING_ENTITY : "ofrece"
    SCORING_ENTITY ||--o{ MARKET_SCORING_ENTITY : "está en"
```