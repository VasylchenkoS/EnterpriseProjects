--liquibase formatted sql
--changeset v.vasylchenko:select_query runOnChange:true failOnError:true

SELECT id_employee, surname, name, birth, phone, salary, position
FROM employee INNER JOIN position ON employee.id_position = position.id_position
WHERE name='';

SELECT surname, name, date, tablesit.number
FROM ordering INNER JOIN employee ON ordering.id_employee = employee.id_employee
INNER JOIN tablesit ON ordering.id_table = tablesit.id_table;