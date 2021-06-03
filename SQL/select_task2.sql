-- 1.Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол.
-- Вывести: model, speed и hd

SELECT model, speed, hd FROM pc where price < 500;

-- 2.Найдите производителей принтеров. Вывести: maker

SELECT DISTINCT maker FROM product WHERE type = 'printer';

-- 3.Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает
-- 1000 дол

SELECT model, ram, screen FROM Laptop WHERE price > 1000;

-- 4.Найдите все записи таблицы Printer для цветных принтеров.

SELECT * FROM Printer WHERE color = 'y';

-- 5.Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену
-- менее 600 дол.

SELECT model, speed, hd FROM PC WHERE cd IN ('12x', '24x') AND price < 600;

-- 6.Укажите производителя и скорость для тех ПК-блокнотов, которые имеют жесткий диск объемом не
-- менее 10 Гбайт.

SELECT DISTINCT p.maker, l.speed
FROM laptop l
JOIN product p ON p.model = l.model
WHERE l.hd >= 10;

-- 7.Найдите номера моделей и цены всех продуктов (любого типа), выпущенных производителем B
-- (латинская буква).

SELECT DISTINCT product.model, pc.price
FROM Product JOIN pc ON product.model = pc.model WHERE maker = 'B'
UNION
SELECT DISTINCT product.model, laptop.price
FROM product JOIN laptop ON product.model=laptop.model WHERE maker='B'
UNION
SELECT DISTINCT product.model, printer.price
FROM product JOIN printer ON product.model=printer.model WHERE maker='B';

-- 8.Найдите производителя, выпускающего ПК, но не ПК-блокноты.

SELECT DISTINCT maker
FROM product
WHERE type = 'pc' AND maker NOT IN (
SELECT DISTINCT product.maker
FROM product
Where type = 'laptop'
);

-- 9.Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker

SELECT DISTINCT product.maker
FROM pc
INNER JOIN product ON pc.model = product.model
WHERE pc.speed >= 450;

-- 10.Найдите принтеры, имеющие самую высокую цену. Вывести: model, price

SELECT model, price
FROM printer
WHERE price =
(SELECT MAX(price)
FROM printer );

-- 11.Найдите среднюю скорость ПК.

SELECT AVG(speed) FROM pc;

-- 12.Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол.

SELECT AVG(speed)
FROM laptop
WHERE price > 1000;

-- 13.Найдите среднюю скорость ПК, выпущенных производителем A.

SELECT AVG(pc.speed)
FROM pc, product
WHERE pc.model = product.model AND product.maker = 'A';

-- 14.Для каждого значения скорости найдите среднюю стоимость ПК с такой же скоростью процессора.
-- Вывести: скорость, средняя цена

SELECT speed AND AVG(price) 
FROM pc 
GROUP BY speed;

-- 15.Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD

SELECT hd FROM pc GROUP BY (hd) HAVING COUNT(model) >= 2;

-- 16.Найдите пары моделей PC, имеющих одинаковые скорость и RAM. В результате каждая пара
-- указывается только один раз, т.е. (i,j), но не (j,i), Порядок вывода: модель с большим номером,
-- модель с меньшим номером, скорость и RAM

SELECT DISTINCT p1.model, p2.model, p1.speed, p1.ram
FROM pc p1, pc p2
WHERE p1.speed = p2.speed AND p1.ram = p2.ram AND p1.model > p2.model;


-- 17.Найдите модели ПК-блокнотов, скорость которых меньше скорости любого из ПК.
-- Вывести: type, model, speed

SELECT DISTINCT p.type,p.model,l.speed
FROM laptop l
JOIN product p ON l.model=p.model
WHERE l.speed<(SELECT min(speed)
FROM pc);

-- 18.Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price

SELECT DISTINCT product.maker, printer.price
FROM product, printer
WHERE product.model = printer.model
AND printer.color = 'y'
AND printer.price = (
SELECT MIN(price) FROM printer
WHERE printer.color = 'y'
);

-- 19.Для каждого производителя найдите средний размер экрана выпускаемых им ПК-блокнотов.
-- Вывести: maker, средний размер экрана.

SELECT
product.maker, AVG(screen)
FROM laptop
LEFT JOIN product ON product.model = laptop.model
GROUP BY product.maker;

-- 20.Найдите производителей, выпускающих по меньшей мере три различных модели ПК. Вывести:
-- Maker, число моделей

SELECT maker, COUNT(model)
FROM product
WHERE type = 'pc'
GROUP BY product.maker
HAVING COUNT (model) >= 3;

-- 21.Найдите максимальную цену ПК, выпускаемых каждым производителем. Вывести: maker,
-- максимальная цена.

SELECT product.maker, MAX(pc.price)
FROM product, pc
WHERE product.model = pc.model
GROUP BY product.maker;

-- 22.Для каждого значения скорости ПК, превышающего 600 МГц, определите среднюю цену ПК с такой
-- же скоростью. Вывести: speed, средняя цена.

SELECT pc.speed, AVG(pc.price)
FROM pc
WHERE pc.speed > 600
GROUP BY pc.speed;

-- 23.Найдите производителей, которые производили бы как ПК со скоростью не менее 750 МГц, так и
-- ПК-блокноты со скоростью не менее 750 МГц. Вывести: Maker

SELECT DISTINCT maker
FROM product t1 JOIN pc t2 ON t1.model=t2.model
WHERE speed>=750 AND maker IN
( SELECT maker
FROM product t1 JOIN laptop t2 ON t1.model=t2.model
WHERE speed>=750 );

-- 24.Перечислите номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в
-- базе данных продукции.

SELECT model
FROM (
 SELECT model, price
 FROM pc
 UNION
 SELECT model, price
 FROM Laptop
 UNION
 SELECT model, price
 FROM Printer
) t1
WHERE price = (
 SELECT MAX(price)
 FROM (
  SELECT price
  FROM pc
  UNION
  SELECT price
  FROM Laptop
  UNION
  SELECT price
  FROM Printer
  ) t2
 );

-- 25.Найдите производителей принтеров, которые производят ПК с наименьшим объемом RAM и с
-- самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM. Вывести: Maker

SELECT DISTINCT maker
FROM product
WHERE model IN (
SELECT model
FROM pc
WHERE ram = (
  SELECT MIN(ram)
  FROM pc
  )
AND speed = (
  SELECT MAX(speed)
  FROM pc
  WHERE ram = (
   SELECT MIN(ram)
   FROM pc
   )
  )
)
AND
maker IN (
SELECT maker
FROM product
WHERE type='printer'
);