# short_urls schema

# --- !Ups

CREATE TABLE short_urls (
    hashval varchar(255) NOT NULL,
    full_url varchar(1024) NOT NULL DEFAULT '',
    PRIMARY KEY (hashval)
);


# --- !Downs

DROP TABLE short_urls;
