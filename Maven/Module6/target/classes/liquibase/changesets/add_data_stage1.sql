--liquibase formatted sql
--changeset v.vasylchenko:add_data_stage1 runOnChange:true failOnError:true


INSERT INTO Menu(name) VALUES
  ('WinterMenu'), ('SpringMenu'),('SummerMenu'),('AutumnMenu');

INSERT INTO Position(Position) VALUES
  ('Director'),('Manager'),('Waiter'),('Cook'),('Cleaner');

INSERT INTO orderstate (state) VALUES
  ('Open'),
  ('Close'),
  ('Deferred');

INSERT INTO Employee (Surname, Name, Birth, Phone, ID_Position, Salary) VALUES
  ('Kogan','Igor','1968-05-23','651-48-47', (SELECT ID_Position FROM Position WHERE Position='Director'),35000),
  ('Herkku','Wartian','1988-05-23','652-23-42', (SELECT ID_Position FROM Position WHERE Position='Manager'),25000),
  ('Importadora','Wellington','1982-05-23','234-35-684', (SELECT ID_Position FROM Position WHERE Position='Cook'),20000),
  ('Koskitalo','Pirkko','1994-05-23','135-48-47', (SELECT ID_Position FROM Position WHERE Position='Cook'),20000),
  ('Parente','Paula','1995-05-23','035-35-15', (SELECT ID_Position FROM Position WHERE Position='Waiter'),15000),
  ('Jablonski','Karl','1999-05-23','864-18-48', (SELECT ID_Position FROM Position WHERE Position='Waiter'),15000),
  ('Zbyszek','Wolski','1978-05-23','186-35-64', (SELECT ID_Position FROM Position WHERE Position='Cleaner'),5000);


INSERT INTO TableSit (Number) VALUES
  ('#1'), ('#2'), ('#3'), ('#4'), ('#5'), ('#6'), ('#7'), ('#8'), ('#9'), ('#10');


INSERT INTO Category (Category) VALUES ('Alcohol'), ('First'), ('Salad'), ('Snack'), ('Beer'),
  ('Second');

INSERT INTO Storage (Ingredient, Quantity) VALUES
  ('Water',5000), ('Salt',5000), ('Sugar', 5000),('Sauce', 5000),
  ('Fish', 10), ('Meat', 20), ('Vegetables', 50), ('Fruits',70), ('Snacks',50),
  ('Alcohol', 40), ('Juice', 40), ('Beer',70);


INSERT INTO Dish (Name, ID_Category, Price, Weigth) VALUES
  ('Soup',(SELECT ID_Category FROM Category WHERE Category='First'),25.50,0.350),
  ('Free Potato',(SELECT ID_Category FROM Category WHERE Category='Second'),32,0.250),
  ('French Meat',(SELECT ID_Category FROM Category WHERE Category='Second'),79.30,0.750),
  ('Gold Fish',(SELECT ID_Category FROM Category WHERE Category='Second'),130,1.150),
  ('Caprize',(SELECT ID_Category FROM Category WHERE Category='Salad'),50,0.250),
  ('Summer Salad',(SELECT ID_Category FROM Category WHERE Category='Salad'),35,0.350),
  ('Chips',(SELECT ID_Category FROM Category WHERE Category='Snack'),25,0.150),
  ('Cheese Free',(SELECT ID_Category FROM Category WHERE Category='Snack'),45,0.450),
  ('Absolute',(SELECT ID_Category FROM Category WHERE Category='Alcohol'),450,1.000),
  ('Hennesy',(SELECT ID_Category FROM Category WHERE Category='Alcohol'),1350,0.750),
  ('Red Label',(SELECT ID_Category FROM Category WHERE Category='Alcohol'),750,1.000),
  ('Leaf 0,33',(SELECT ID_Category FROM Category WHERE Category='Beer'),75,0.330),
  ('Leaf 0,5',(SELECT ID_Category FROM Category WHERE Category='Beer'),120,0.500),
  ('Hoegaarden 0,5',(SELECT ID_Category FROM Category WHERE Category='Beer'),120,0.500);



INSERT INTO DishIngredients (ID_Dish, ID_Ingridient) VALUES
  ((SELECT ID_Dish FROM Dish WHERE Name='Soup'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Water')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Soup'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Salt')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Soup'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Meat')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Soup'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Vegetables')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Free Potato'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Salt')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Free Potato'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Vegetables')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Free Potato'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Sauce')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Gold Fish'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Salt')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Gold Fish'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Fish')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Gold Fish'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Vegetables')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Gold Fish'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Sauce')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Summer Salad'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Salt')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Summer Salad'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Vegetables')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Summer Salad'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Sauce')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Caprize'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Salt')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Caprize'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Vegetables')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Caprize'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Sauce')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Chips'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Snacks')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Cheese Free'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Snacks')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Absolute'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Alcohol')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Hennesy'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Alcohol')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Red Label'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Alcohol')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Leaf 0,33'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Beer')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Leaf 0,5'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Beer')),
  ((SELECT ID_Dish FROM Dish WHERE Name='Hoegaarden 0,5'), (SELECT ID_Ingridient FROM Storage WHERE Ingredient='Beer'));
