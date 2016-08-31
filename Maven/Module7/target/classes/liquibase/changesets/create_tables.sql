--liquibase formatted sql
--changeset v.vasylchenko:create_tables runOnChange:true failOnError:true

CREATE TABLE Storage(
  ID_Ingridient       SERIAL PRIMARY KEY     NOT NULL,
  Ingredient  VARCHAR(50)    NOT NULL UNIQUE ,
  Quantity    INT
);

CREATE TABLE Category(
  ID_Category          SERIAL PRIMARY KEY     NOT NULL,
  Category VARCHAR(50) NOT NULL  UNIQUE
);

CREATE TABLE Dish(
  ID_Dish          SERIAL PRIMARY KEY     NOT NULL,
  Name  VARCHAR(50) NOT NULL UNIQUE,
  ID_Category INT,
  Price DOUBLE PRECISION,
  Weigth REAL
);

CREATE TABLE DishIngredients(
  ID          SERIAL PRIMARY KEY     NOT NULL,
  ID_Dish     INT NOT NULL ,
  ID_Ingridient INT NOT NULL
);

CREATE TABLE Menu(
  ID_Menu          SERIAL PRIMARY KEY     NOT NULL ,
  Name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE MenuDish(
  ID          SERIAL PRIMARY KEY     NOT NULL,
  ID_Menu INT,
  ID_Dish INT
);

CREATE TABLE Position(
  ID_Position          SERIAL PRIMARY KEY     NOT NULL,
  Position VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Employee(
  ID_Employee          SERIAL PRIMARY KEY     NOT NULL,
  Surname VARCHAR(50) NOT NULL,
  Name VARCHAR(50) NOT NULL ,
  Birth DATE,
  Phone VARCHAR(50),
  ID_Position INT,
  Salary REAL
);

CREATE TABLE TableSit(
  ID_Table          SERIAL PRIMARY KEY     NOT NULL,
  Number VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE Ordering(
  ID_Order          SERIAL PRIMARY KEY     NOT NULL,
  ID_Employee INT NOT NULL,
  ID_Table INT,
  Date DATE NOT NULL,
  ID_State int not null DEFAULT(1)
);

CREATE TABLE OrderState(
  ID_State          SERIAL PRIMARY KEY     NOT NULL,
  State VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE kitchenDishState(
  ID_State          SERIAL PRIMARY KEY     NOT NULL,
  State VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE OrderDish(
  ID          SERIAL PRIMARY KEY     NOT NULL,
  ID_Order INT NOT NULL,
  ID_Dish INT NOT NULL
);

CREATE TABLE Kitchen(
  ID_Kitchen          SERIAL PRIMARY KEY     NOT NULL,
  ID_Employee INT NOT NULL,
  ID_Order INT,
  Date DATE NOT NULL,
  ID_DishState INT NOT NULL DEFAULT 1,
  id_dish int
);

ALTER TABLE Kitchen ADD COLUMN id_dish INT