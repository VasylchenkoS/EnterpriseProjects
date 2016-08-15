--liquibase formatted sql
--changeset v.vasylchenko:release


INSERT INTO Menu(name) VALUES
  ('SpringMenu'),
  ('SummerMenu'),
  ('AutumnMenu');


INSERT INTO Position(Position) VALUES
  ('Director'),
  ('Manager'),
  ('Waiter'),
  ('Cook'),
  ('Cleaner');

--rollbackCount release1;