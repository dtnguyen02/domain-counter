--DROP TABLE users IF EXISTS;

CREATE SEQUENCE DOMAIN_SEQUENCE;
CREATE SEQUENCE URL_SEQUENCE;

CREATE TABLE domain (
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE DOMAIN_SEQUENCE PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE url (
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE URL_SEQUENCE PRIMARY KEY,
  domain_id INTEGER,
  url  VARCHAR(255)
);

ALTER TABLE domain ADD CONSTRAINT unique_domain_name UNIQUE (name);
ALTER TABLE url ADD CONSTRAINT fk_url_domain_domain_id FOREIGN KEY (domain_id) REFERENCES domain (id);