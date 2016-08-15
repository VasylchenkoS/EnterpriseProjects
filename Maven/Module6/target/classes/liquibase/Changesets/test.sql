--liquibase formatted sql
--changeSet v.vasylchenko:release  tagDatabase:version_0.1.0

CREATE TABLE test(
  id SERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(10)
)
--rollback drop table test;