--liquibase formatted sql
--changeset v.vasylchenko:add_data_stage2 runOnChange:true failOnError:true


INSERT INTO Ordering (ID_Employee, ID_Table, Date) VALUES (
  (SELECT ID_Employee FROM Employee WHERE
    ID_Position=(SELECT ID_Position FROM Position WHERE Position='Waiter') AND Surname='Jablonski'),
  (SELECT ID_Table FROM TableSit WHERE Number='#1'), NOW()
);

INSERT INTO OrderDish (ID_Order, ID_Dish) VALUES
  ((SELECT max(ordering.id_order) FROM ordering),
     (SELECT ID_Dish FROM Dish WHERE Name='Soup' AND check_aviable_ingridients('Soup'))),
  ((SELECT max(id_order) FROM Ordering),
   (SELECT ID_Dish FROM Dish WHERE Name='Caprize'AND check_aviable_ingridients('Caprize'))),
  ((SELECT max(id_order) FROM Ordering),
   (SELECT ID_Dish FROM Dish WHERE Name='Leaf 0,5'AND check_aviable_ingridients('Leaf 0,5')));

INSERT INTO kitchen (id_employee, id_order, date, id_status) VALUES
  ((choose_cook()),
   (SELECT max(ID_Order) FROM Ordering),
  now(),(SELECT id_dishstatus FROM dishstatus WHERE dishstatus='Deferred'));
