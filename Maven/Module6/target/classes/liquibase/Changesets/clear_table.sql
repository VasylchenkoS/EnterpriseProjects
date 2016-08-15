--liquibase formatted sql
--changeset v.vasylchenko:release1

drop table category CASCADE ;
drop table dish CASCADE ;
drop table dishingredients CASCADE ;
drop table employee CASCADE ;
drop table kitchen CASCADE ;
drop table menu CASCADE ;
drop table menudish CASCADE ;
drop table orderdish CASCADE ;
drop table ordering CASCADE ;
drop table position CASCADE ;
drop table storage CASCADE ;
drop table tablesit CASCADE ;