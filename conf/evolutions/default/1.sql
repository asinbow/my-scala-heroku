# counters schema

# --- !Ups

CREATE TABLE counters (
    keyword varchar(255) NOT NULL,
    count bigint NOT NULL DEFAULT 0,
    PRIMARY KEY (keyword)
);

INSERT INTO counters(keyword, count) VALUES('visit', 0);

# --- !Downs

DROP TABLE counters;
